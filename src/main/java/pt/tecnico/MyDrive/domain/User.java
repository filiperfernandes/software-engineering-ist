package pt.tecnico.MyDrive.domain;

import java.io.UnsupportedEncodingException;

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
		for (User u: md.getUserSet())
			md.removeUser(u);

		// user.setUsername(username) nao deve interessar por agora

		/*try {
			setUsername(new String(personElement.getAttribute("username").getValue().getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			throw new ImportDocumentException();
		}*/

		/*for (Element node: element.getChildren("user")) {
			String username = node.getAttribute("username").getValue();*/

		String username = personElement.getAttribute("username").getValue();

		String password = personElement.getAttribute("password").getValue();

		String name = personElement.getAttribute("name").getValue();

		//Element user = personElement.getChild("user");

		User u1 = new User(username, password, name);
		/*
		for (Element contactElement: contacts.getChildren("contact"))
			new Contact(this, contactElement);

		for (Element contactElement: contacts.getChildren("email-contact"))
			new EmailContact(this, contactElement);
	}*/

	}
}
