package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.FileIsDirectoryException;
import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.App;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.Link;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Session;

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
		Session session = Session.getSessionByToken(token);
		Directory dir = session.getCurrentdir() ;

		File file = (dir.getFileByName(name));
		checkPermissionsRead(session.getUser(), file.getUser(), file.getPermissions());
		if(file instanceof Directory){
			throw new FileIsDirectoryException();
		}
		else if(file instanceof PlainFile){
			fileData = ((PlainFile)file).getData();
		}		
		else if(file instanceof Link){
			fileData = ((Link)file).getData();
		}
		else if(file instanceof App){
			fileData = ((App)file).getData();
		}
	}

	public String result(){
		return fileData;
	}


}