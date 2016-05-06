package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.LoginUserService;

public class Login extends MdCommand{

	public Login(Shell sh) { super(sh, "login", "create a session for the user"); }

	@Override
	void execute(String[] args) {

		new LoginUserService(args[0], args[1]).execute();
	}

}
