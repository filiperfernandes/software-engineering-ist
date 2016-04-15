package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.*;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Directory;

public class ReadFileService extends MyDriveService{

	private String name;
	private long token;
	private String fileData;

	public ReadFileService(String name, long token) {
		this.name=name;
		this.token=token;
	}

	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();
		Session session = getSessionByToken(token);
		Directory dir = session.getCurrentdir() ;
		
		PlainFile file = ((PlainFile) (dir.getPlainfileByName(name)));
		checkPermissionsRead(session.getUser(), file.getUser(), file.getPermissions());
		fileData = file.getData();
	}
	
	public String result(){
		return fileData;
	}
	
	public static Session getSessionByToken(long token){
		MyDrive md = MyDrive.getInstance();

		for(Session s : md.getSessionSet()){
			if(s.getToken()==token){
				return s;
			}
		}
		throw new SessionDoesNotExistException(token);
	}
}