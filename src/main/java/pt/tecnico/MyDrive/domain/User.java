package pt.tecnico.MyDrive.domain;

import java.lang.String;
import org.jdom2.Element;


public class User extends User_Base {

	protected User(){}

	public User(String username, String password, String name ) {
		super();
		MyDrive md = MyDrive.getInstance();

		if (checkValidString(username)==false || checkValidString(password)==false || checkValidString(name)==false){
			return;
			//Need tthrow an exception
		}

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

		Directory dir = new Directory(md.getCnt(),username, "rwxdr-x-");
		this.addFile(dir);

		Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		home.addFile(dir);
    md.addUser(this);

	}

	public String ToString(){
		return (getUsername() + " - " + getName() + " - " + getMask());

	}

	public String changeUsername(String newUsername){

		if (checkValidString(newUsername)==false){
			return "Not a valid String";
			//exception?
		}
		else if (newUsername.equals("root")){
			return "error that name is reserved";
			//throw exceptions?
		}
		else if (newUsername.equals(getName())){
			return "your name was already that one";
			//throw exceptions?
		}
		else{
			setUsername(newUsername);
			// setFilename(newUsername);
			return  "sucess";
		}

		// falta verificar se o username pedido ja se encontra em uso+++
	}


	public String changeName(String newName){
		if (checkValidString(newName)==false){
			return "Not a valid String";
			//exception?
		}
		else{
			setName(newName);
			return "sucess";
		}
	}

	public boolean checkValidString(String check){

		for(int i=0; i<check.length();i++){
			int asciiCheck = (int) check.charAt(i);

			if (asciiCheck > 47 && asciiCheck < 58 || asciiCheck > 64 && asciiCheck <  91 || asciiCheck > 96 && asciiCheck < 123) {
				continue;
			}
			return false;
			//throw a exception  
		}
		return true;
	}


	public User(MyDrive md, String name) {
		super();
		setName(name);
		setMydrive(md);
	}


	public User(MyDrive md, Element xml) {
		super();
		xmlImport(xml);
		setMydrive(md);
	}

	public void xmlImport(Element personElement) /*throws ImportDocumentException */{

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
		
		new User(username, password, name);
		

	}

	public Element xmlExport() {
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
}
