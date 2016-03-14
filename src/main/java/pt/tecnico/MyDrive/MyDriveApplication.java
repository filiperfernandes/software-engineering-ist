package pt.tecnico.MyDrive;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.io.PrintStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.joda.time.DateTime;

import java.util.Scanner;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.MyDrive.Exception.*;


import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.User;

public class MyDriveApplication {
	//static final Logger log = LogManager.getRootLogger();
	private static Scanner input = new Scanner(System.in);


	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {

			setup();
			display();

			for (String s: args) xmlScan(new java.io.File(s));
			
			addData();
			xmlPrint();

		} finally { FenixFramework.shutdown(); }
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

			int selection = input.nextInt();
			input.nextLine();

			switch (selection) {
			case 1:
				listUsers();
				break;
			case 2:
				newUser();
				break;
			case 3:
				newDirectory();
				break;
			case 4:
				newPlainFile();
				break;
			case 5:
				listDirectory();
				/*System.out.println("Insert path of directory");
				listDirectory(input.next());
				 */	
				break;
			case 6:
				changeCurrentDirectory();
				//System.out.println("Insert name directory");
				//removeDirectory(input.next());
				break;
			case 7:
				/*MyDrive md = MyDrive.getInstance();
				System.out.println((md.getCurrentdir()).getName());*/
				//removeFile();
				break;
			case 8:
				//removePlainFile();
				System.out.println("Insert name directory");
				removePlainFile(input.next());
				break;
			case 9:
				readPlainFile();
				//System.out.println("Insert path of file");
				//readPlainFile(input.next());
				break;
			case 0:
				quit = false;
				System.out.println("Exit Success!");
				break;
			default:
				System.out.println("Invalid selection.");
				break;
			}
			//			while(quit);
			//			System.out.println("Exit Success!");
		}
	}
	@Atomic
	public static void changeCurrentDirectory(){
		MyDrive md = MyDrive.getInstance();

		System.out.println("Insert name of Directory you want to go");
		String name = input.next();
		if(name.equals(".")){
			System.out.println("Still on same Directory");
		}
		else if(name.equals("..")){
			md.setCurrentdir((md.getCurrentdir()).getDirectory());
			System.out.println("Changed to previous Directory");
		}
		else {
			for(pt.tecnico.MyDrive.domain.File dir : (md.getCurrentdir()).getFileSet()){
				if(name.equals(dir.getName())){
					if(dir instanceof Directory ){
						md.setCurrentdir((Directory) dir);
						System.out.println("Changed current Directory to " + name);
						break;
					}
					else{
						// Exception
						System.out.println("The File by that name is a PlainFile");
					}
				}
			}
			System.out.println("No File by that name in your current Directory");
		}
	}



	@Atomic
	public static void newUser(){
		System.out.println("Insert username of new User");
		String username = input.next();
		System.out.println("Insert name of new User");
		String name = input.next();
		System.out.println("Insert password of new User");
		String pass = input.next();
		try{
			User u = new User(username, pass, name);
			System.out.println("ola");
		}catch(InvalidStringException | InvalidPasswordException e){ System.err.println(e); }
		
	}

	@Atomic
	public static void listUsers(){
		MyDrive md = MyDrive.getInstance();
		for(User u : md.getUserSet()){
			System.out.println(u.getUsername());
		}
	}

	@Atomic
	public static void newDirectory(){
		MyDrive md = MyDrive.getInstance();
		System.out.println("Insert name of new directory");
		String name = input.next();
		Directory d = new Directory(md.getCnt(), name, "rwxdr-x-");
		(md.getCurrentdir()).addFile(d);
		(md.getCurrentuser()).addFile(d);
	}

	@Atomic
	public static void listDirectory(){
		System.out.println("Insert path of directory");
		String path = input.next();
		MyDrive md = MyDrive.getInstance();
		String dirname = "";
		Directory dir = md.getRootdir() ;
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				try{
					dir = (Directory) (dir.getFileByName(dirname));
					dirname="";
				}catch (FileDoesNotExistException e) { System.err.println(e); }
			}
			else{
				dirname += ch;
			}
		}
		try{
			dir = (Directory) (dir.getFileByName(dirname));
			System.out.println(".\n..");
			for(pt.tecnico.MyDrive.domain.File f : dir.getFileSet()){
				System.out.println(f.getName());
			}
		}catch (FileDoesNotExistException e) { System.err.println(e); }
		
	}

	@Atomic
	public static void newPlainFile(){
		MyDrive md = MyDrive.getInstance();
		System.out.println("Insert name of new PlainFile");
		String name = input.next();
		System.out.println("Insert data of new PlainFile");
		String data = input.next();
		PlainFile f = new PlainFile(md.getCnt(), name, "rwxdr-x-", data);
		(md.getCurrentdir()).addFile(f);
		(md.getCurrentuser()).addFile(f);
	}


	@Atomic
	public static void readPlainFile() {
		System.out.println("Insert path of file");
		String path = input.next();
		MyDrive md = MyDrive.getInstance();
		String dirname = "";
		Directory dir = md.getRootdir() ;
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				try{
				dir = (Directory) (dir.getFileByName(dirname));
				dirname="";
				}catch(FileDoesNotExistException e) { System.err.println(e); }
			}
			else{
				dirname += ch;
			}
		}
		try{
		System.out.println(((PlainFile) (dir.getFileByName(dirname))).getData());
		}catch(FileDoesNotExistException e) { System.err.println(e); }
	}

	
	//Initialize MyDrive
	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();
		PlainFile file = new PlainFile(md.getCnt(), "test","rwxdr-test", "Hello World!");
		Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		Directory root = (Directory) (home.getFileByName("root"));
		Directory d = new Directory(md.getCnt(), "casa","rwxdr-test");

		root.addFile(d);

		root.addFile(file);

		new User("joao", "passe", "vultos");

		//new SuperUser();

		//createPlainFile("root", "/home/root/test", "/home/root", "rwxdr-test", "Hello World!");
		//r.setDimension(r.getDimension()+1);

		//new User("Filipe", "test", "Filipe Fernandes");


	}

	//Add data to database
	@Atomic
	public static void addData(){
		MyDrive md = MyDrive.getInstance();
		
		//new PlainFile(md.getCnt(), owner, name, pathToFile, date, "rwxdr-test", "Hello World!");
		//Directory f = (Directory)md.getFileByPath(pathToFile);

		User u= new User("filiperfernandes", "fPW", "Filipe");
		
		PlainFile p = new PlainFile(md.getCnt(), "Hello", "rwxdr-x-", "HelloWorld"); 
		
		//u.addFile(p);
		
	}
	
	//Not sure if needed
	@Atomic
	public static Directory getHome(){
		MyDrive md = MyDrive.getInstance();
		Directory home = (Directory) md.getRootdir().getFileByName("home");
		System.out.println("APP O HOME E:" + home);
		return home;
	}


	//Export XML
	@Atomic
    public static void xmlPrint() {
	Document doc = MyDrive.getInstance().xmlExport();
	XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
	try { xmlOutput.output(doc, new PrintStream(System.out));
	} catch (IOException e) { System.out.println(e); }
    }
	
	//Import XML
	@Atomic
	public static void xmlScan(File file) {

		MyDrive md = MyDrive.getInstance();

		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document)builder.build(file);
			md.xmlImport(document.getRootElement());
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}


	@Atomic
	public static void removeDirectory(String name ) {
		MyDrive md = MyDrive.getInstance();
		System.out.println("Name1: "+name);
		
		if(((md.getCurrentdir()).equals(null))) 
		{ 
			//throws IOException 
			System.out.println("Excepção Activa!");
			System.out.println("Name2: "+name);

		}else if ((md.getCurrentdir()).getFileByName(name).equals(null))
		{
			//throws IOException 
			System.out.println("Excepção Activa!");
			System.out.println("Name3: "+name);

		}else { 
			//Remove Directory
			System.out.println("Name - Removido1: "+name);
			(md.getCurrentdir()).removeFile((md.getCurrentdir()).getFileByName(name));
			System.out.println("Name - Removido2: "+name);
			//Continua nao remover

		}
	}

	@Atomic
	public static void removePlainFile(String name){ //Remove PlainFile
		MyDrive md = MyDrive.getInstance();
		(md.getCurrentdir()).removeFile((md.getCurrentdir()).getFileByName(name));
	}   //Continua nao remover

}
