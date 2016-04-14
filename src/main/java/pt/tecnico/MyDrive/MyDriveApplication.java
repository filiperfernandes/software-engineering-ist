package pt.tecnico.MyDrive;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.MyDrive.Exception.*;



import pt.tecnico.MyDrive.domain.*;




public class MyDriveApplication {
	//static final Logger log = LogManager.getRootLogger();
	private static Scanner input = new Scanner(System.in);

	//static MyDrive md;

	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {
			System.out.println("VOufazer setup");

			setup();
			display();
			/*			for (String s: args) xmlScan(new java.io.File(s));


			xmlPrint();
			 */
		} finally { FenixFramework.shutdown(); }
	}


	@Atomic
	public static long login(String username, String pass) throws UsernameDoesNotExistException , InvalidPasswordException{
		MyDrive md = MyDrive.getInstance();	
		//try{
		Session session = new Session(md, username, pass);
		return session.getToken();
		//}catch(UsernameDoesNotExistException | InvalidPasswordException e) {System.err.println(e);}
		//return 0;
	}


	@Atomic
	public static Session getSessionByToken(long token){
		MyDrive md = MyDrive.getInstance();

		for(Session s : md.getSessionSet()){
			if(s.getToken()==token){
				return s;
			}
		}
		throw new SessionDoesNotExistException(token);
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
					removeDirectory();
					break;
				case 8:
					removePlainFile();
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


	@Atomic
	public static String changeCurrentDirectory(String name, long tok){
		MyDrive md = MyDrive.getInstance();
		Session session = getSessionByToken(tok);
		Directory dir = session.getCurrentdir();
		Directory rd = md.getRootdir();

		if(name.equals(".")){
          	return dir.getPath();
		}
		
		
		else if(name.equals("..")){
			dir = dir.getDirectory();
			session.setCurrentdir(dir);
			return dir.getPath();
		}
		else {
			if(checkPath(name, dir).equals("absolute")|| checkPath(name, dir).equals("")){
				Directory directory = getDirByPath(name, rd);
				session.setCurrentdir(directory);
				System.out.println("absolute");
				return directory.getPath();
			}
			else if(checkPath(name, dir).equals("relative") || checkPath(name, dir).equals("")){
				Directory directory = getDirByPath(name, dir);
				session.setCurrentdir(directory);
				return directory.getPath();
			}
			else{
				return "null";
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
	public static void listDirectory(long token){
		MyDrive md = MyDrive.getInstance();
		Directory dir = md.getRootdir() ;
		Integer c = 0;
		


		try{
			Session s= getSessionByToken(token);
			dir = s.getCurrentdir();
			System.out.println(".\n..");
			for(pt.tecnico.MyDrive.domain.File f : dir.getFileSet()){
				System.out.println(f.getName());
			}
		}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }

	}


	@Atomic
	public static void ReadFile(String name, long tok) {
		
		MyDrive md = MyDrive.getInstance();
		Session session = getSessionByToken(tok);
		Directory dir = session.getCurrentdir() ;
		
		
		try{
			PlainFile file = ((PlainFile) (dir.getPlainfileByName(name)));
			checkPermissionsRead(session.getUser(), file.getUser(), file.getPermissions());
			System.out.println(file.getData());
		}catch(FileDoesNotExistException | FileIsDirectoryException | UserDoesNotHavePermissionsException e) { System.err.println(e); }
	}
	
	public static void WriteFile(String name, long tok,String content) {
		
		MyDrive md = MyDrive.getInstance();		
		Session session = getSessionByToken(tok);
		Directory dir = session.getCurrentdir() ;
		
		try{
			PlainFile file = ((PlainFile) (dir.getPlainfileByName(name)));
			checkPermissionsWrite(session.getUser(), file.getUser(), file.getPermissions());
			file.setData(content);
		}catch(FileDoesNotExistException | FileIsDirectoryException | UserDoesNotHavePermissionsException e) { System.err.println(e); }
	}


	//Initialize MyDrive
	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();

		long p =login("root","***");
		Session s = getSessionByToken(p);
		Directory d = s.getCurrentdir();
		User u = s.getUser();
		Directory dir = new Directory(7, "joao","rwxd--x-" );
		d.addFile(dir);
		//String path = changeCurrentDirectory("/home", p);
		//System.out.println(path);
		PlainFile file = new PlainFile(md.getCnt(), "test","rwxdr-test", "Hello World!");
		d.addFile(file);
		u.addFile(file);
		ReadFile("test",p);
		WriteFile("test",p,"HI !!!!!!!");
		ReadFile("test",p);
		createFile(p, "joao", "Directory", null);
		//Directory dir = new Directory(7, "joao","rwxd--x-" );
		//d.addFile(dir);
		//String path = changeCurrentDirectory("/joao", p);
		//System.out.println(path);


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
		rd.getPermissions();
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
		return "";
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

public static boolean checkPermissionsRead(User user, User owner, String permissions){
	char ch1[] = permissions.toCharArray();
	String userPermissions = user.getMask();
	char ch2[] = userPermissions.toCharArray();

	if(owner.equals(user)){
		return true;	
	}
	else if (ch1[5]==ch2[5] && ch1[5]=='r'){
		return true;
	}
	else{
		throw new UserDoesNotHavePermissionsException();
	}

}

public static boolean checkPermissionsWrite(User user, User owner, String permissions){
	char ch1[] = permissions.toCharArray();
	String userPermissions = user.getMask();
	char ch2[] = userPermissions.toCharArray();

	if(owner.equals(user)){
		return true;	
	}
	else if (ch1[6]==ch2[6] && ch1[6]=='w'){
		return true;
	}
	else{
		throw new UserDoesNotHavePermissionsException();
	}
}

public static boolean checkPermissionsExecute(User user, User owner, String permissions){
	char ch1[] = permissions.toCharArray();
	String userPermissions = user.getMask();
	char ch2[] = userPermissions.toCharArray();

	if(owner.equals(user)){
		return true;	
	}
	else if (ch1[7]==ch2[7] && ch1[7]=='x'){
		return true;
	}
	else{
		return false;
	}
}
public static boolean checkPermissionsDelete(User user, User owner, String permissions){
	char ch1[] = permissions.toCharArray();
	String userPermissions = user.getMask();
	char ch2[] = userPermissions.toCharArray();

	if(owner.equals(user)){
		return true;	
	}
	else if (ch1[8]==ch2[8] && ch1[8]=='d'){
		return true;
	}
	else{
		return false;
	}
}


@Atomic
public static void createFile(long token, String name, String type, String content){
	MyDrive md = MyDrive.getInstance();
	Session s = getSessionByToken(token);
	User user = s.getUser();
	Directory dir = s.getCurrentdir();
	String perm = user.getMask();



	try{
		checkPermissionsWrite(user,dir.getUser(),dir.getPermissions());
		
		char namecheck[] = name.toCharArray();
		int i=0;
		while(namecheck[i] != '\0'){
			if(namecheck[i]== '/' || namecheck[i]=='.'){
				throw new InvalidStringException(name);
			}
			i++;
			
		}

		if(type.equals("Directory")){

			Directory d = new Directory(md.getCnt(), name, perm);
			dir.addFile(d);
			user.addFile(d);

		}
		else if(type.equals("PlainFile")){

			PlainFile f = new PlainFile(md.getCnt(), name, perm, content);
			dir.addFile(f);
			user.addFile(f);

		}
		else{
			return;
		}

	}
	catch(InvalidStringException e){ System.err.println(e);}
}

}

