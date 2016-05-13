package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.LoginUserService;

public class Key extends MdCommand{

	private MdShell s;
	
	public Key(Shell sh) {
		super(sh, "token", "'key <token>' to change session of user"); 
		s= ((MdShell) sh);
	}
	
	@Override
	public void execute(String[] args) {
		if (args.length < 1){
			System.out.println(s.getSessionToken() +" "+ s.getSessionUser());
		}

		else{
			s.setSessionToken(s.getTokenByUsername(args[0]));
			s.setSessionUser(args[0]);
			System.out.println(s.getSessionToken() +" "+ s.getSessionUser());
		}
	}

}
