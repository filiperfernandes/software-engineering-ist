package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.LoginUserService;

public class Key extends MdCommand{

	private MdShell s;
	
	public Key(Shell sh) {
		super(sh, "token", "'key <token>' to change session of user"); 
		s= ((MdShell) sh);
	}
	
	@Override
	void execute(String[] args) {
		long key=0;
		if (args.length < 1){
			throw new RuntimeException("USAGE: "+name()+" <token> [username]");
		}

		if (args.length >1){
			LoginUserService l = new LoginUserService(args[0], args[1]);
			l.execute();	
			key = l.result();
			
		}
		System.out.println(s);
		System.out.println(key);
	}

}
