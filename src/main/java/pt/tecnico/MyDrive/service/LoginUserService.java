package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;

public class LoginUserService extends MyDriveService{

	private String username;
	private String password;
	private long token;

	public LoginUserService(String username, String password) {
		this.username=username;
		this.password=password;
	}

	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();
		Session s = new Session(username, password);
		md.addSession(s);
		token = s.getToken();
		
	}


	public final long result() {
		return token;
	}
}
