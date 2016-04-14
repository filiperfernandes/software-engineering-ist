package pt.tecnico.MyDrive.service;

import javax.annotation.Generated;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.service.MyDriveService;

public class DeleteFileService extends MyDriveService {

	private String fileName;

	public DeleteFileService (long token, String name) {
		fileName = name;
	}

	public final void dispatch() throws FileDoesNotExistException {
		//etPlainFile(fileName).remove();
	}
}

/*
@Atomic
	public static Session getSessionByToken(long token){
		MyDrive md = MyDrive.getInstance();

		for(Session s : md.getSessionSet()){
			if(s.getToken()==token){
				return s;
			}
		}
		throw new SessionDoesNotExistException(token);
	}
 */