package pt.tecnico.MyDrive;

import java.io.IOException;

import org.joda.time.DateTime;

import java.util.Scanner;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.SuperUser;
import pt.tecnico.MyDrive.domain.User;
import pt.tecnico.MyDrive.domain.PlainFile;

public class MyDriveApplication {
	//static final Logger log = LogManager.getRootLogger();
	private static Scanner input = new Scanner(System.in);


	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {

			setup();
			display();


			//File c = new File(md.getCounter(), "c", "/home/root", "/home/root", date, "rwxdr-x-");
			//md.setCounter(md.getCounter()+1);

		} finally { FenixFramework.shutdown(); }
	}

	@Atomic
	public static void display() {
		boolean  quit = true;
		while(quit){
			System.out.println("-- MENU --");
			System.out.println(
					"Select an option: \n" +
							"  1) New File\n" +
							"  2) New User\n" +
							"  3) New Directory\n" +
							"  4) New Plain File\n" +
							"  5) List Directories\n " +
							" 6) Remove Directory\n" +
							"  7) Remove File\n" +
							"  8) Remove Plain File\n" +
							"  9) Show Plain File\n" +
					"  0) EXIT\n");

			int selection = input.nextInt();
			input.nextLine();

			switch (selection) {
			case 1:
				//newFile();
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
				System.out.println("Insert path of directory");
				listDirectory(input.next());
				break;
			case 6:
				//removeDirectory();
				System.out.println("Insert name directory");
				removeDirectory(input.next());
				break;
			case 7:
				//removeFile();

				break;
			case 8:
				//removePlainFile();
				System.out.println("Insert name directory");
				removePlainFile(input.next());
				break;
			case 9:
				System.out.println("Insert path of file");
				readPlainFile(input.next());
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

	/*private static void removeDirectory() {
		// TODO Auto-generated method stub
		}*/

	@Atomic
	public static void listDirectory(String path){
		MyDrive md = MyDrive.getInstance();
		String dirname = "";
		Directory dir = md.getRootdir() ;
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				dir = (Directory) (dir.getFileByName(dirname));
				dirname="";
			}
			else{
				dirname += ch;
			}
		}
		dir = (Directory) (dir.getFileByName(dirname));
		System.out.println(".\n..");
		for(File f : dir.getFileSet()){
			System.out.println(f.getName());
		}
	}



	@Atomic
	public static void readPlainFile(String path) {
		MyDrive md = MyDrive.getInstance();
		String dirname = "";
		Directory dir = md.getRootdir() ;
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				dir = (Directory) (dir.getFileByName(dirname));
				dirname="";
			}
			else{
				dirname += ch;
			}
		}

		System.out.println(((PlainFile) (dir.getFileByName(dirname))).getData());
	}


	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();
		PlainFile file = new PlainFile(md.getCnt(), "test","rwxdr-test", "Hello World!");
		Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		Directory root = (Directory) (home.getFileByName("root"));
		Directory d = new Directory(md.getCnt(), "casa","rwxdr-test");

		root.addFile(d);

		root.addFile(file);

		//new SuperUser();

		//createPlainFile("root", "/home/root/test", "/home/root", "rwxdr-test", "Hello World!");
		//r.setDimension(r.getDimension()+1);

		//new User("Filipe", "test", "Filipe Fernandes");

	}


	/*public static void createPlainFile(String owner, String name, String pathToFile, String permissions, String data){
	@Atomic
		MyDrive md = MyDrive.getInstance();
		DateTime date = new DateTime();

		//new PlainFile(md.getCnt(), owner, name, pathToFile, date, "rwxdr-test", "Hello World!");
		//Directory f = (Directory)md.getFileByPath(pathToFile);



	}*/

	/*	@Atomic
	public static void test1() {

		MyDrive md = MyDrive.getInstance();

		DateTime date = new DateTime();

	}
}

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
	}*/

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

		}}

	@Atomic
	public static void removePlainFile(String name){ //Remove PlainFile
		MyDrive md = MyDrive.getInstance();
		(md.getCurrentdir()).removeFile((md.getCurrentdir()).getFileByName(name));
	}   //Continua nao remover
}


