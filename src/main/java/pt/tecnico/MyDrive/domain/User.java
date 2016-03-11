package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

public class User extends User_Base {
    
	protected User(){}
	
    public User(String username, String password, String name, String mask, String homeDir) {
        super();
		DateTime date = new DateTime();

        setUsername(username);
        setPassword(password);
        setName(name);
        setMask(mask);
        setHomeDir(homeDir);
    }
    
}
