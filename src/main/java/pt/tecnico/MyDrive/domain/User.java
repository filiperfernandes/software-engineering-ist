package pt.tecnico.MyDrive.domain;

import java.lang.String;
import org.joda.time.DateTime;


public class User extends User_Base {
	private String username;

	protected User(){}

	public User(String username, String password, String name ) {
		super();
		MyDrive md = MyDrive.getInstance();

		if (checkValidString(username)==false || checkValidString(password)==false || checkValidString(name)==false){
			return;
			//Need tthrow an exception
		}

		setUsername(username);

		if (password == null){
			setPassword(username);
		}
		else{
			setPassword(password);
		}

		if(name==null){
			setName(username);
		}
		else{
			setName(name);
		}   

		setMask("rwxd----");

		Directory dir = new Directory(md.getCnt(),username, "rwxdr-x-");
		this.addFile(dir);

		Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		home.addFile(dir);
    md.addUser(this);

	}

	public String ToString(){
		return (username + " - " + getName() + " - " + getMask());

	}

	public String changeUsername(String newUsername){

		if (checkValidString(newUsername)==false){
			return "Not a valid String";
			//exception?
		}
		else if (newUsername.equals("root")){
			return "error that name is reserved";
			//throw exceptions?
		}
		else if (newUsername.equals(getName())){
			return "your name was already that one";
			//throw exceptions?
		}
		else{
			setUsername(newUsername);
			// setFilename(newUsername);
			return  "sucess";
		}

		// falta verificar se o username pedido ja se encontra em uso+++
	}


	public String changeName(String newName){
		if (checkValidString(newName)==false){
			return "Not a valid String";
			//exception?
		}
		else{
			setName(newName);
			return "sucess";
		}
	}

	public boolean checkValidString(String check){

		for(int i=0; i<check.length();i++){
			int asciiCheck = (int) check.charAt(i);

			if (asciiCheck > 47 && asciiCheck < 58 || asciiCheck > 64 && asciiCheck <  91 || asciiCheck > 96 && asciiCheck < 123) {
				continue;
			}
			return false;
			//throw a exception  
		}
		return true;
	}

}
