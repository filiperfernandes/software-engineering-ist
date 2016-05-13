package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.FileIsNotAppException;
import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.PathDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.App;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.Link;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Session;

public class WriteFileService extends MyDriveService{

	private String path;
	private String content;
	private long token;
	private PlainFile ficheiro;

	public WriteFileService(String name, long token, String content) {
		this.path=name;
		this.content=content;
		this.token=token;
	}

	@Override
	protected void dispatch() throws MyDriveException {
		Session session = getSessionByToken(token);
		Directory dir = session.getCurrentdir() ;	

		MyDrive md = MyDrive.getInstance();
		Directory rd = md.getRootdir();
		
		String [] parts = path.split("/");
		if (parts.length==1) {
			PlainFile file = ((PlainFile) (dir.getPlainfileByName(path)));
			checkPermissionsWrite(session.getUser(), file.getUser(), file.getPermissions());
			file.setData(content);
			ficheiro = file;
		}
		else{
			int i=1;
			dir=rd;
			while(i<(parts.length-1)){
				dir=(Directory) dir.getDirByName(parts[i]);
				i++;
			}
			PlainFile file = ((PlainFile) (dir.getPlainfileByName(parts[parts.length-1])));
			checkPermissionsWrite(session.getUser(), file.getUser(), file.getPermissions());
			file.setData(content);
			ficheiro = file;
		}
		
			
		
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
