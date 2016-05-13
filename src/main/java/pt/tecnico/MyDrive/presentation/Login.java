package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.LoginUserService;

public class Login extends MdCommand{
	
	private MdShell s;

	public Login(Shell sh) { 
		super(sh, "login", "create a session for the user");
		s = (MdShell) sh;
	}

	@Override
	public void execute(String[] args) {
		if (args.length < 1){
		    throw new RuntimeException("USAGE: "+name()+" <username> [password]");
		}
		
		if (args.length >1){
			LoginUserService l = new LoginUserService(args[0], args[1]);
			l.execute();
			s.addSessionToMap(args[0], l.result());
			s.setSessionToken(l.result());
			s.setSessionUser(args[0]);
		}
		
		else{
			LoginUserService l = new LoginUserService(args[0],"");
			l.execute();
			s.addSessionToMap(args[0], l.result());
			s.setSessionToken(l.result());
			s.setSessionUser(args[0]);
		}

	}
}
