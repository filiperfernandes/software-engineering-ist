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
				//newDirectory();
				break;
			case 4:
				//newPlainFile();
				break;
			case 5:
				listDirectory();
				break;
			case 6:
				changeCurrentDirectory();
				break;
			case 7:
				removeDirectory();
				break;
			case 8:
				removePlainFile();
				break;
			case 9:
				readPlainFile();
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
			for(File dir : (md.getCurrentdir()).getFileSet()){
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
		User u = new User(username, pass, name);
	}

	@Atomic
	public static void listUsers(){
		MyDrive md = MyDrive.getInstance();
		for(User u : md.getUserSet()){
			System.out.println(u.getUsername());
		}
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

		new User("joao", "passe", "vultos");

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
	public static void removeDirectory() {
		MyDrive md = MyDrive.getInstance();
		System.out.println("Insert name of Directory you want to remove:");
		String name_dir = input.next();
		boolean exists =false;
		if((((md.getCurrentdir()).getDimension()).equals(1))) 
		{ 
			//throws IOException 
			System.out.println("Excepção Activa!");

		}else {//(!(md.getCurrentdir()).getFileByName(name_dir).equals(null))

			for (File f: (md.getCurrentdir()).getFileSet()){
				if (f.getName().equals(name_dir)){
					if (f instanceof Directory){
						if  ( ( ( (Directory) ( (md.getCurrentdir()).getFileByName(name_dir) ) ).getDimension() ).equals(2)){						
							Directory dir = (Directory) (md.getCurrentdir()).getFileByName(name_dir);
							(md.getCurrentuser()).removeFile(dir);
							(md.getCurrentdir()).removeFile(dir);
							dir.removeDir();
							exists = true;
							break;
						}else {
							//throws IOException que não é directorio é ficheiro
						}
					}else {
						//throws IOException que não é directorio é ficheiro
					}
				}
			}
			if (!exists){
				//throws IOException 
				System.out.println("Excepção Activa!");
			}
		}
	}

	@Atomic
	public static void removePlainFile(){ //Remove PlainFile
		MyDrive md = MyDrive.getInstance();
		System.out.println("Insert name of PlainFile you want to remove:");
		String name_rmplfile = input.next();
		boolean exists =false;
		if((((md.getCurrentdir()).getDimension()).equals(1))) 
		{ 
			//throws IOException 
			System.out.println("Excepção Activa!");

		}else {//(!(md.getCurrentdir()).getFileByName(name_dir).equals(null))

			for (File f: (md.getCurrentdir()).getFileSet()){
				if (f.getName().equals(name_rmplfile)){
					if (f instanceof PlainFile){
						PlainFile plfile = (PlainFile) (md.getCurrentdir()).getFileByName(name_rmplfile);
						(md.getCurrentuser()).removeFile(plfile);
						(md.getCurrentdir()).removeFile(plfile);
						plfile.removePlainFile();
						exists = true;
						break;

					}
				}else {
					//throws IOException que não é directorio é ficheiro
				}
			}
		}
		if (!exists){
			//throws IOException 
			System.out.println("Excepção Activa!");
		}
	}
}

   //Continua nao remover

