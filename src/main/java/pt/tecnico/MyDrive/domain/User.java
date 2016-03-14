package pt.tecnico.MyDrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;


public class User extends User_Base {

	protected User(){}

	public User(String username, String password, String name ) {
		super();
		DateTime date = new DateTime();
		MyDrive md = MyDrive.getInstance();

		setUsername(username);
		setPassword(password);
		setName(name);
		setMask("rwxd----");
		
		md.addUser(this);
		
		Directory newD = new Directory(md.getCnt(), username, date, "rwxdr-x-");
		this.addFile(newD);
		
		Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		home.addFile(newD);

		//new Directory(md.getCnt(),username, "/home/"+ username, "/home", date, "rwxdr-x-");
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

		// clear current phone book | current mydrive
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
