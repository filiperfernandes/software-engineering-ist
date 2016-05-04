package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.domain.App;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;

public class ExecuteFileService extends MyDriveService{


	private long token;
	private String path;
	private String[] args;

	public ExecuteFileService(long token, String path, String[] args){
		this.token=token;
		this.path=path;
		this.args=args;

	}
	@Override
	protected void dispatch() throws MyDriveException {
		if(path==null){
			throw new NullArgumentException();
		}
		else if(args==null){
			throw new NullArgumentException();
		}
		MyDrive md = MyDrive.getInstance();
		
		Session s = Session.getSessionByToken(token);
		
//		Sabendo o path ir buscar a App e fazer o .Run(args);

		
	}

}
