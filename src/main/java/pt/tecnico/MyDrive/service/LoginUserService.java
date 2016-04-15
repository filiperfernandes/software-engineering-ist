package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;

public class LoginUserService extends MyDriveService{

	private String username;
	private String password;
	private MyDrive md;
	private long token;

	public LoginUserService(MyDrive md, String username, String password) {
		this.username=username;
		this.password=password;
		this.md=md;
	}

	@Override
	protected void dispatch() throws MyDriveException {
		Session s = new Session(md, username, password);
		token = s.getToken();
	}


	public final long result() {
		return token;
	}
}
