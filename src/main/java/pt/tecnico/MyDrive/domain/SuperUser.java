package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

public class SuperUser extends SuperUser_Base {
    
    public SuperUser() {
        super();
        
        
		DateTime date = new DateTime();
		/*MyDrive md = MyDrive.getInstance();
        md.setCounter(1);
        Integer counter = md.getCounter();*/
        
       // Directory rootdir = new Directory(counter, "root", "/home/root", "/home/root", date, "rwxdr-x-", 2, ". ..");
        
        setUsername("root");
        setPassword("***");
        setName("Super User");
        setMask("rwxdr-x-");
        setHomeDir("/home/root");
    }
    
    
}
