package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.FileIsNotAppException;
import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.Exception.PathDoesNotExistException;
import pt.tecnico.MyDrive.Exception.PermissionDeniedException;
import pt.tecnico.MyDrive.domain.App;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.Link;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.User;
import java.util.*;

public class ExecuteFileService extends MyDriveService{


	private long token;
	private String path;
	private String[] args;

	public ExecuteFileService(long token, String path, String... arg){
		String[] args = new String[10];
		int i=0;
		for(String v : arg){
			
			args[i]=v;
			i++;
		}

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
		Directory dir = s.getCurrentdir();
		Directory rd = md.getRootdir();
		User u = s.getUser();
		App app;

		String name = "";
		String p = "";
		Integer c = 0;

		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}

			else if (ch=='/'){
				p += "/" + name ;
				name = "";
			}
			else{
				name += ch;
			}
		}


		if(Link.checkPath(path, dir).equals("absolute")){
			Directory directory = Directory.getDirByPath(p, rd);
			File f = directory.getFileByName(name);
			if(f instanceof App){
				app =(App) f;
			}
			else{
				throw new FileIsNotAppException();
			}

		}
		else if(Link.checkPath(path, dir).equals("relative")){
			Directory directory = Directory.getDirByPath(p, dir);
			File f = directory.getFileByName(name);
			if(f instanceof App){
				app =(App) f;
			}
			else{
				throw new FileIsNotAppException();
			}

		}
		else{
			throw new PathDoesNotExistException(path);
		}

		if(app.checkPermissionsExecute(u, app.getUser(), app.getPermissions())){
			app.Run(args);
		}
		else{
			throw new PermissionDeniedException();
		}

	}


}
