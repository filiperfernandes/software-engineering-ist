package pt.tecnico.MyDrive.domain;

public class User extends User_Base {
    
	protected User(){}
	
    public User(String username, String password, String name, String mask, String homeDir) {
        super();
        setUsername(username);
        setPassword(password);
        setName(name);
        setMask(mask);
        setHomeDir(homeDir);
    }
    
}
