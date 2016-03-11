package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

public class SuperUser extends SuperUser_Base {
    
    public SuperUser() {
        super();
        
        
        MyDrive md = MyDrive.getInstance();
		DateTime date = new DateTime();
		
		Directory rootDir = new Directory(md.getCnt(),"/", date, "rwxdr-x-");
		md.setRootdir(rootDir);
		Directory home = new Directory(md.getCnt(), "home", date, "rwxdr-x-");
		rootDir.addFile(home);
        
        Directory rd = new Directory(md.getCnt(), "root", date, "rwxdr-x-");
        
        home.addFile(rd);
        
        setUsername("root");
        setPassword("***");
        setName("Super User");
        setMask("rwxdr-x-");
        this.addFile(rd);
        
    }
    
    
}
