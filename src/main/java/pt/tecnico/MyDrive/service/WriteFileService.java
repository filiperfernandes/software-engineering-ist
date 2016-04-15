package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.*;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.File;

public class WriteFileService extends MyDriveService{

	private String name;
	private String content;
	private long token;
	private PlainFile ficheiro;

	public WriteFileService(String name, long token, String content) {
		this.name=name;
		this.content=content;
		this.token=token;
	}

	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();		
		Session session = getSessionByToken(token);
		Directory dir = session.getCurrentdir() ;

		PlainFile file = ((PlainFile) (dir.getPlainfileByName(name)));
		checkPermissionsWrite(session.getUser(), file.getUser(), file.getPermissions());
		file.setData(content);
		
		ficheiro = file;
	}
	
	public PlainFile result(){
		return ficheiro;
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
