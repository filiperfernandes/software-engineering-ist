package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.LoginUserService;

public class Login extends MdCommand{

	public Login(Shell sh) { super(sh, "login", "create a session for the user"); }

	@Override
	void execute(String[] args) {
		if (args.length < 1){
		    throw new RuntimeException("USAGE: "+name()+" <username> [password]");
		}
		
		if (args.length >1){
			new LoginUserService(args[0], args[1]).execute();
		}
		
		else{
			new LoginUserService(args[0],"").execute();
		}

	}

}
