package pt.tecnico.MyDrive.domain;

public class SuperUser extends User {
    
    public SuperUser() {
        super("root", "***", "Super User", "rwxdr-x-", "/home/root");
        /*setUsername("root");
        setPassword("***");
        setName("Super User");
        setMask("rwxdr-x-");
        setHomeDir("/home/root");*/
    }
    
}
