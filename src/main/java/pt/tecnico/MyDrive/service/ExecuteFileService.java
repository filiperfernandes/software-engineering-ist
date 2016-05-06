package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.Exception.PathDoesNotExistException;
import pt.tecnico.MyDrive.Exception.PermissionDeniedException;
import pt.tecnico.MyDrive.domain.App;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.Link;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.User;

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
			app =(App) directory.getFileByName(name);

		}
		else if(Link.checkPath(path, dir).equals("relative")){
			Directory directory = Directory.getDirByPath(p, dir);
			app =(App) directory.getFileByName(name);

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