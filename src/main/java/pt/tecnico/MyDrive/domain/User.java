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
		
		
		// String home = personElement.getChildText("home");nao deve ser preciso
		
		//Directory newD = new Directory(md.getCnt(), username, date, mask);
		//Directory x = md.getRootdir();
		
		

		//Element user = personElement.getChild("user");
		
		//System.out.println(user.getAttributeValue("password"));

		new User(username, password, name);
		/*
		for (Element contactElement: contacts.getChildren("contact"))
			new Contact(this, contactElement);

		for (Element contactElement: contacts.getChildren("email-contact"))
			new EmailContact(this, contactElement);
	}*/

	}
}
