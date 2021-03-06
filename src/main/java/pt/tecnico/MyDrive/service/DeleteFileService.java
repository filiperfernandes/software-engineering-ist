package pt.tecnico.MyDrive.service;


import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Session;

public class DeleteFileService extends MyDriveService {

	private String fileName;
	private long token;

	public DeleteFileService (long token, String name) {
		this.fileName = name;
		this.token=token;

	}

	public final void dispatch() throws FileDoesNotExistException {
		Session session = getSessionByToken(token);
		Directory dir = session.getCurrentdir() ;
		pt.tecnico.MyDrive.domain.File file = dir.getFileByName(fileName);
		checkPermissionsDelete(session.getUser(), file.getUser(), file.getPermissions());

		if (file instanceof Directory) {
			((Directory) file).removeDir();	

		}
		else if (file instanceof PlainFile) {
			((PlainFile) file).removePlainFile();

		}

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