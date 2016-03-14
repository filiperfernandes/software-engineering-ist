package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

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
		home.addFile(rd);

		setUsername("root");
		setPassword("***");
		setName("Super User");
		setMask("rwxdr-x-");
		this.addFile(rd);
		md.setCurrentdir(rd);
		md.setCurrentuser(this);
		md.addUser(this);
	}


}
