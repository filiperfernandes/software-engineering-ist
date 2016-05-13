package pt.tecnico.MyDrive.domain;


public class SuperUser extends SuperUser_Base {
	//protected SuperUser (){}

	public SuperUser() {
		super();

		MyDrive md = MyDrive.getInstance();

		Directory rootDir = new Directory(md.getCnt(),"/", "rwxdr-x-");
		md.setRootdir(rootDir);
		Directory home = new Directory(md.getCnt(), "home", "rwxdr-x-");
		
		rootDir.addFile(home);

		Directory rd = new Directory(md.getCnt(), "root", "rwxdr-x-");
		//PlainFile pf = new PlainFile(10, "Casa", "rwxdr-x-", "Olá");
		
		home.addFile(rd);
		
		//rd.addFile(pf);
		//this.addFile(pf);
		
		setUsername("root");
		setPassword("***");
		setName("Super User");
		setMask("rwxdr-x-");
		this.setHomedir(rd);
		rootDir.setUser(this);
		rd.setUser(this);
		home.setUser(this);
		md.addUser(this);
		
		
		PlainFile a = new PlainFile(md.getCnt(), "teste", "rwxdr-x-", "123");
		rd.addFile(a);
		
		this.addFile(a);
	}


}
