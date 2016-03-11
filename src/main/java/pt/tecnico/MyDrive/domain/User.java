package pt.tecnico.MyDrive.domain;

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
    
}
