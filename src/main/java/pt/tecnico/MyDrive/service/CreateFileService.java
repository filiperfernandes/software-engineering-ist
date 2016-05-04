package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.InvalidStringException;
import pt.tecnico.MyDrive.Exception.InvalidTypeException;
import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.domain.App;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.Link;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Session;
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
		Session s = Session.getSessionByToken(token);
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
		
		else if(type.equals("Link")){
			Link l = new Link(md.getCnt(), name, content, token);
			dir.addFile(l);
			user.addFile(l);
			file = l.getName();
		}	
		else if(type.equals("App")){
			App a = new App(md.getCnt(), name, content, perm);
			dir.addFile(a);
			user.addFile(a);
			file = a.getName();
		}
		
		else{
			throw new InvalidTypeException(); 
		}
	}

	public String result(){
		return file;
	}


}

