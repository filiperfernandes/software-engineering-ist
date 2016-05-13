package pt.tecnico.MyDrive.domain;

import org.jdom2.Element;

import pt.tecnico.MyDrive.Exception.ExportXmlException;
import pt.tecnico.MyDrive.Exception.ImportXmlException;
import pt.tecnico.MyDrive.Exception.InvalidPasswordException;
import pt.tecnico.MyDrive.Exception.InvalidStringException;
import pt.tecnico.MyDrive.Exception.UsernameAlreadyExistsException;
import pt.tecnico.MyDrive.Exception.UsernameDoesNotExistException;


public class User extends User_Base {

	protected User(){}
	//public int check = 0;
	public User(String username, String password, String name, String homedir, String mask ) {
		super();
		MyDrive md = MyDrive.getInstance();


		if (checkValidString(username)==false ){
			throw new InvalidStringException(username);

		}
		else if (username.equals("root") ){
			throw new UsernameAlreadyExistsException(username);
		}
		if (md.getUserSet()!=null){
			for (User user : md.getUserSet()){				
				if(user.getUsername().equals(username)){
					throw new UsernameAlreadyExistsException(username);

				}
			}
		}
		//check++;
		//System.out.println(check);
		setUsername(username);

		if (password == null){
			setPassword(username);
		}
		else{
			setPassword(password);
		}

		if(name==null){
			setName(username);
		}
		else{
			setName(name);
		}   

		if(mask.equals("")){
			setMask("rwxd----");
		}
		
		else if(!mask.equals("")){
			setMask(mask);
		}

		Directory dir;


		if(homedir.equals(null)){
			dir = new Directory(md.getCnt(),username, "rwxdr-x-");
		}
		else{
			if(pt.tecnico.MyDrive.domain.File.fileNameCheck(homedir)==false){
				throw new InvalidStringException(homedir);
			}
			dir = new Directory(md.getCnt(),homedir, "rwxdr-x-");
		}

		this.addFile(dir);
		this.setHomedir(dir);

		Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		home.addFile(dir);
		md.addUser(this);



	}
	
	
	public User(MyDrive md, String username, String password, String name, String homedir ) {
		super();
		
		MyDrive muuu = MyDrive.getInstance();
		//setMydrive(muuu);

		if (checkValidString(username)==false ){
			throw new InvalidStringException(username);

		}
		else if (username.equals("root") ){
			throw new UsernameAlreadyExistsException(username);
		}
		if (muuu.getUserSet()!=null){
			for (User user : muuu.getUserSet()){				
				if(user.getUsername().equals(username)){
					throw new UsernameAlreadyExistsException(username);

				}
			}
		}
		//check++;
		//System.out.println(check);
		setUsername(username);

		if (password == null){
			setPassword(username);
		}
		else{
			setPassword(password);
		}

		if(name==null){
			setName(username);
		}
		else{
			setName(name);
		}   

		setMask("rwxd----");

		Directory dir;


		if(homedir.equals(null)){
			dir = new Directory(muuu.getCnt(),username, "rwxdr-x-");
		}
		else{
			if(pt.tecnico.MyDrive.domain.File.fileNameCheck(homedir)==false){
				throw new InvalidStringException(homedir);
			}
			dir = new Directory(muuu.getCnt(),homedir, "rwxdr-x-");
		}

		this.addFile(dir);
		this.setHomedir(dir);

		Directory home = (Directory) (muuu.getRootdir()).getFileByName("home");
		home.addFile(dir);
		muuu.addUser(this);



	}
	
	public boolean checkPassword( String pass){
		if(this.getPassword().equals(pass)){
			return true;
		}
		else{
			throw new InvalidPasswordException();
		}
	}

	public String ToString(){
		return (getUsername() + " - " + getName() + " - " + getMask());

	}


	public static User getUserByUsername(String username){
		MyDrive md = MyDrive.getInstance();
		for (User user : md.getUserSet()){
			if(user.getName().equals(username)){
				return user;

			}
		}
		throw new UsernameDoesNotExistException(username);		
	}


	public String changeUsername(String newUsername){


		if (checkValidString(newUsername)==false){
			throw new InvalidStringException(newUsername);
		}
		else if (newUsername.equals("root")){
			throw new UsernameAlreadyExistsException(newUsername);
		}
		for (User user : getMydrive().getUserSet()){
			if(user.getUsername().equals(newUsername)){
				throw new UsernameAlreadyExistsException(newUsername);

			}
		}

		setUsername(newUsername);
		// setFilename(newUsername);
		return  "sucess";
	}

	// falta verificar se o username pedido ja se encontra em uso+++



	public String changeName(String newName) {
		if (checkValidString(newName)==false){
			throw new InvalidStringException(newName);

		}
		else{
			setName(newName);
			return "sucess";
		}
	}


	public boolean checkValidString(String check) {

		for(int i=0; i<check.length();i++){
			int asciiCheck = (int) check.charAt(i);
			if (asciiCheck > 47 && asciiCheck < 58 || asciiCheck > 64 && asciiCheck <  91 || asciiCheck > 96 && asciiCheck < 123) {
				continue;
			}

			return false;

		}
		return true;

	}


	public User(MyDrive md, String name) {
		super();
		setUsername(name);
		setMydrive(md);
	}


	public User(MyDrive md, Element xml) {
		super();
		xmlImport(xml);
		setMydrive(md);
	}

	public void xmlImport(Element personElement) throws ImportXmlException {

		MyDrive md = MyDrive.getInstance();

		// clear current MyDrive
		for (User u: md.getUserSet()){
			System.out.println("vai remover " + u);
			md.removeUser(u);
			System.out.println("removeu user "+ u);
		}
		// user.setUsername(username) nao deve interessar por agora

		/*try {
			setUsername(new String(personElement.getAttribute("username").getValue().getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			throw new ImportDocumentException();
		}*/
		/*
		for (Element node: personElement.getChildren("password")) {
			String password = node.getValue();}


		for (Element node: personElement.getChildren("name")) {
			String name = node.getValue();}*/

		String username = personElement.getAttribute("username").getValue();

		String password = personElement.getChildText("password");

		String name = personElement.getChildText("name");

		new User(username, password, name, "null","");


	}

	public Element xmlExport() throws ExportXmlException {
		Element userElement = new Element("user");
		userElement.setAttribute("username", getUsername());


		//Password
		Element userPassword = new Element("password");
		userPassword.setText(getPassword());
		userElement.addContent(userPassword);

		//Name
		Element uName = new Element("name");
		uName.setText(getName());
		userElement.addContent(uName);

		//Home
		Element uHome = new Element("home");
		uHome.setText("/home/"+getUsername());
		userElement.addContent(uHome);

		//Mask
		Element uMask = new Element("mask");
		uMask.setText("rwxdr-x-");
		userElement.addContent(uMask);


		return userElement;
	}
	public void remove() {
		MyDrive md = MyDrive.getInstance();
		for (User u :md.getUserSet()){
			for (File d :  u.getFileSet()){
				if (d instanceof PlainFile){
					((PlainFile) d).removePlainFile();
				}else{
				//d.remove();
				((Directory) d).removeDir();}
				//u.removeFile(d);
				//setMydrive(null);
				//deleteDomainObject();
			}
			for (Session s: u.getSessionSet()){
				s.remove();
			}
			//Remove users
			u.setHomedir(null);
			setMydrive(null);
			deleteDomainObject();
		}
		
	}
	
	public static void listUsers(){
		MyDrive md = MyDrive.getInstance();
		for(User u : md.getUserSet()){
			System.out.println(u.getUsername());
		}
	}
	
	
}
