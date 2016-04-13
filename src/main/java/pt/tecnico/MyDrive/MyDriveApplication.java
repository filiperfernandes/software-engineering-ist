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

import java.util.InputMismatchException;
import java.util.Scanner;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.MyDrive.Exception.*;


import pt.tecnico.MyDrive.domain.Directory;

import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Sessao;
import pt.tecnico.MyDrive.domain.User;

public class MyDriveApplication {
	//static final Logger log = LogManager.getRootLogger();
	private static Scanner input = new Scanner(System.in);


	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {

			setup();
			long p =login();
			System.out.println(p);
			//	Sessao s = getSessaoByToken(p);
			//Directory d = s.getCurrentdir();
			//Directory dir = new Directory(7, "joao","rwxd--x-" );
			//d.addFile(dir);
			String s = changeCurrentDirectory("/home", p);
			System.out.println(s);
			//display();
			/*			for (String s: args) xmlScan(new java.io.File(s));


			xmlPrint();
			 */
		} finally { FenixFramework.shutdown(); }
	}
	@Atomic
	public static long login(){
		new Sessao();
		MyDrive md = MyDrive.getInstance();

		boolean quit = true;
		while(quit){
			System.out.println("-- Login --");
			System.out.println("Insert Username");
			String username = input.next();
			System.out.println("Insert password");
			String pass = input.next();
			try{
				User user = md.getUserByUsername(username);
				if(user.CheckPassword(pass)){
					System.out.println("Login Successful");
					Sessao sessao = new Sessao();
					md.addSessao(sessao);
					user.addSessao(sessao);
					sessao.setCurrentdir(user.getHomedir());
					return sessao.getToken();
				}
			}catch(UsernameDoesNotExistException | InvalidPasswordException e) {System.err.println(e);}

		}
		return 0;
	}


	public static Sessao getSessaoByToken(long token){
		MyDrive md = MyDrive.getInstance();

		for(Sessao s : md.getSessaoSet()){
			if(s.getToken().equals(token)){
				return s;
			}
		}
		throw new SessaoDoesNotExistException(token);
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
					break;
				case 6:	
					System.out.println("Insert name of Directory you want to go");
					String name = input.next();
					//changeCurrentDirectory(name,654376);
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
			}
		}


	}


	@Atomic
	public static String changeCurrentDirectory(String name, long tok){
		MyDrive md = MyDrive.getInstance();
		Sessao sessao = getSessaoByToken(tok);
		Directory dir = sessao.getCurrentdir();
		Directory rd = md.getRootdir();

		if(name.equals(".")){
			return dir.getPath();
		}
		else if(name.equals("..")){
			dir = dir.getDirectory();
			sessao.setCurrentdir(dir);
			return dir.getPath();
		}
		else {
			if(checkPath(name, dir).equals("absolute")){
				Directory directory = getDirByPath(name, rd);
				sessao.setCurrentdir(directory);
				return directory.getPath();
			}
			else if(checkPath(name, dir).equals("relative")){
				Directory directory = getDirByPath(name, dir);
				sessao.setCurrentdir(directory);
				return directory.getPath();
			}
			else{
				throw new DirectoryDoesNotExistException(name);
			}
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
		System.out.println("Insert name of User's HomeDir");
		String homedir = input.next();

		try{
			User u = new User(username, pass, name, homedir);
		}catch(InvalidStringException | UsernameAlreadyExistsException e){ System.err.println(e); }

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
					dir = (Directory) (dir.getDirByName(dirname));
					dirname="";
				}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }
			}
			else{
				dirname += ch;
			}
		}
		try{
			dir = (Directory) (dir.getDirByName(dirname));
			System.out.println(".\n..");
			for(pt.tecnico.MyDrive.domain.File f : dir.getFileSet()){
				System.out.println(f.getName());
			}
		}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }

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
			System.out.println(((PlainFile) (dir.getPlainfileByName(dirname))).getData());
		}catch(FileDoesNotExistException | FileIsDirectoryException e) { System.err.println(e); }
	}


	//Initialize MyDrive
	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();
		//PlainFile file = new PlainFile(md.getCnt(), "test","rwxdr-test", "Hello World!");
		//Directory home = (Directory) (md.getRootdir()).getDirByName("home");
		//Directory root = (Directory) (home.getFileByName("root"));
		//Directory d = new Directory(md.getCnt(), "casa","rwxdr-test");
		//md.setCurrentdir(md.getRootdir());
		//root.addFile(d);

		//root.addFile(file);

		//new User("joao", "passe", "vultos");

		//new SuperUser();

		//createPlainFile("root", "/home/root/test", "/home/root", "rwxdr-test", "Hello World!");
		//r.setDimension(r.getDimension()+1);

		//new User("Filipe", "test", "Filipe Fernandes");


	}

	/*public static void createPlainFile(String owner, String name, String pathToFile, String permissions, String data){

	//Add data to database

	@Atomic
	public static void addData(){
		MyDrive md = MyDrive.getInstance();

		//new PlainFile(md.getCnt(), owner, name, pathToFile, date, "rwxdr-test", "Hello World!");
		//Directory f = (Directory)md.getFileByPath(pathToFile);






	}*/



	//Not sure if needed
	@Atomic
	public static Directory getHome(){

		MyDrive md = MyDrive.getInstance();
		Directory home = (Directory) md.getRootdir().getDirByName("home");
		System.out.println("APP O HOME E:" + home);
		return home;


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

	@Atomic
	public static void removeDirectory() {
		System.out.println("Insert path of directory you want to remove");
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
					dir = (Directory) (dir.getDirByName(dirname));
					dirname="";
				}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }
			}
			else{
				dirname += ch;
			}
		}	
		try{
			dir = (Directory) (dir.getDirByName(dirname));
			if(dir.DirectoryEmpty(dir));{
				(md.getCurrentuser()).removeFile(dir);
				(md.getCurrentdir()).removeFile(dir);
				dir.removeDir();
				System.out.println("remove sucessfull");
			}
		}catch(DirectoryDoesNotExistException | FileIsPlainFileException | DirectoryIsNotEmptyException e) { 
			System.err.println(e); }
	}





	@Atomic
	public static void removePlainFile(){ //Remove PlainFile
		System.out.println("Insert path of plainfile you want to remove");
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
			PlainFile plfile = (PlainFile) (dir.getPlainfileByName(dirname));
			(md.getCurrentuser()).removeFile(plfile);
			(md.getCurrentdir()).removeFile(plfile);
			plfile.removePlainFile();
			System.out.println("remove sucessfull");
		}catch(FileDoesNotExistException | FileIsDirectoryException e) { 
			System.err.println(e); }
	}

	public static String checkPath(String path, Directory dir){

		MyDrive md = MyDrive.getInstance();
		Directory rd = md.getRootdir();
		String dirname = "";
		Integer c = 0;
		String auxname = path + "/";
		for(char ch : auxname.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				for (pt.tecnico.MyDrive.domain.File d : rd.getFileSet()){
					if(d.getName().equals(dirname)){
						if(d instanceof Directory ){
							return "absolute";
						}
					}
				}
				for (pt.tecnico.MyDrive.domain.File d2 : dir.getFileSet()){
					if(d2.getName().equals(dirname)){
						if(d2 instanceof Directory ){
							return "relative";
						}
					}
				}
				break;
			}
			else{
				dirname += ch;
			}
		}
		return "wrong path";
	}

	public static Directory getDirByPath(String path, Directory dir){
		String dirname = "";
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				try{
					dir = (Directory) (dir.getDirByName(dirname));
					dirname="";
				}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }
			}
			else{
				dirname += ch;
			}
		}
		try{
			dir = (Directory) (dir.getDirByName(dirname));
		}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }
		return dir;

	}
}