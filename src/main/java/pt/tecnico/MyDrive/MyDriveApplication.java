package pt.tecnico.MyDrive;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.MyDrive.Exception.ExportXmlException;
import pt.tecnico.MyDrive.Exception.ImportXmlException;
import pt.tecnico.MyDrive.domain.Link;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.service.CreateFileService;
import pt.tecnico.MyDrive.service.LoginUserService;


public class MyDriveApplication {
	static final Logger log = LogManager.getRootLogger();
	private static Scanner input = new Scanner(System.in);

	//static MyDrive md;

	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {
			//System.out.println("VOufazer setup");

			setup();
			//display();
			/*			for (String s: args) xmlScan(new java.io.File(s));


			xmlPrint();
			 */
		} finally { FenixFramework.shutdown(); }
	}

	@Atomic
	public static void init() { // empty MyDrive
		log.trace("Init: " + FenixFramework.getDomainRoot());
		MyDrive.getInstance().cleanup();
	}

	@Atomic
	public static void display() {
		PlainFile p = new PlainFile(null, null, null, null);
		boolean  quit = true;
		while(quit){

			System.out.println("-- MENU --");
			System.out.println(
					"Select an option: \n" +
							"  1) List Users\n" +
							"  2) New User\n" +
							"  3) New Directory\n" +
							"  4) New Plain File\n" +
							"  5) List Directories\n" +
							"  6) Change Current Directory\n" +
							"  7) Remove Directory\n" +
							"  8) Remove Plain File\n" +
							"  9) Show Plain File\n" +
					"  0) EXIT\n");

			if(!input.hasNextInt()){
				System.out.println("Invalid Selection");
				input.nextLine();


			}
			else{
				int selection = input.nextInt();
				input.nextLine();

				switch (selection) {
				case 1:
					//listUsers();
					break;
				case 2:
					//newUser();
					break;
				case 3:
					//newDirectory();
					break;
				case 4:
					//newPlainFile();
					break;
				case 5:
					//listDirectory();
					break;
				case 6:	
					System.out.println("Insert name of Directory you want to go");
					String name = input.next();
					//changeCurrentDirectory(name,654376);
					break;
				case 7:
					//removeDirectory();
					break;
				case 8:
					//removePlainFile();
					break;
				case 9:
					System.out.println("Insert path of file");
					String path = input.next();
					//ReadFile(path,p);
					break;
				case 0:
					quit = false;
					System.out.println("Exit Success!");
					break;
				default:
					System.out.println("Invalid selection.");
					break;

				}
			}
		}


	}




	//Initialize MyDrive
	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();
		
		//Teste
		LoginUserService ls = new LoginUserService(md, "root", "***");
		ls.execute();
		long t = ls.result();
		Session s = Session.getSessionByToken(t);
		CreateFileService f = new CreateFileService(t, "ola", "PlainFile", "content");
		f.execute();
		Link l = new Link(md.getCnt(), "tumae", "/home/root/ola", t);
		System.out.println(l.getPermissions());
	}


	//Export XML
	@Atomic
	public static void xmlPrint() {
		Document doc = MyDrive.getInstance().xmlExport();
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		try { xmlOutput.output(doc, new PrintStream(System.out));
		} catch (IOException | ExportXmlException e) { System.err.println(e); }
	}

	//Import XML
	@Atomic
	public static void xmlScan(File file) {

		MyDrive md = MyDrive.getInstance();

		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document)builder.build(file);
			md.xmlImport(document.getRootElement());
		} catch (JDOMException | IOException | ImportXmlException e) { System.err.println(e); }

	}

}

