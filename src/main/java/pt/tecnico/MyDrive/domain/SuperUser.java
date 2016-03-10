package pt.tecnico.MyDrive.domain;

public class SuperUser extends SuperUser_Base {
    
    public SuperUser(String username, String password, String name, String mask, String homeDir) {
        super();
        
        setUsername(username);
        setPassword(password);
        setName(name);
        setMask(mask);
        setHomeDir(homeDir);
    }
    
}
