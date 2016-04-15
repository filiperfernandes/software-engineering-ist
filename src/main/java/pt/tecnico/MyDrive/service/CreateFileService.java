package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.*;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.User;

public class CreateFileService extends MyDriveService{

	private String name;
	private String content;
	private String type;
	private long token;
	private String file;

	public CreateFileService(long token, String name, String type, String content) {
		this.name=name;
		this.content=content;
		this.token=token;
		this.type=type;
	}

	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();
		Session s = getSessionByToken(token);
		User user = s.getUser();
		Directory dir = s.getCurrentdir();
		String perm = user.getMask();
		checkPermissionsWrite(user,dir.getUser(),dir.getPermissions());
		for(char namecheck : name.toCharArray()){
			if(namecheck== '/' || namecheck=='.'){
				throw new InvalidStringException(name);
			}
		}
		if(type.equals("Directory")){
			Directory d = new Directory(md.getCnt(), name, perm);
			dir.addFile(d);
			user.addFile(d);
			file = d.getName();
		}
		else if(type.equals("PlainFile")){
			PlainFile f = new PlainFile(md.getCnt(), name, perm, content);
			dir.addFile(f);
			user.addFile(f);
			file = f.getName();
		}
		else{
			throw new InvalidTypeException(); 
		}
	}

	public String result(){
		return file;
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

