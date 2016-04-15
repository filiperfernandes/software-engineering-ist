package pt.tecnico.MyDrive.service;

import pt.tecnico.MyDrive.Exception.DirectoryDoesNotExistException;
import pt.tecnico.MyDrive.Exception.FileIsPlainFileException;
import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.PathDoesNotExistException;
import pt.tecnico.MyDrive.Exception.PathToBigException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;

public class ChangeDirectoryService extends MyDriveService{


	private String name;
	private long token;
	private String path;

	public ChangeDirectoryService(String name, long token) {
		this.name=name;
		this.token=token;
	} 

	@Override
	protected void dispatch() throws MyDriveException {

		MyDrive md = MyDrive.getInstance();
		Session session = getSessionByToken(token);
		Directory dir = session.getCurrentdir();
		Directory rd = md.getRootdir();
		
		if(name.length()> 1024){
			throw new PathToBigException();
		}
		
		else if (name.equals("/")){
			session.setCurrentdir(rd);
			path = "/";
		}

		else if(name.equals(".")){
			path = dir.getPath();
		}

		else if(name.equals("..")){
			if (dir.equals(rd)){
				path = "/";
			}
			else{
				dir = dir.getDirectory();
				session.setCurrentdir(dir);
				path = dir.getPath();
			}
		}

		else {
			if(checkPath(name, dir).equals("absolute")){
				Directory directory = getDirByPath(name, rd);
				session.setCurrentdir(directory);
				path = directory.getPath();
			}
			else if(checkPath(name, dir).equals("relative")){
				Directory directory = getDirByPath(name, dir);
				session.setCurrentdir(directory);
				path = directory.getPath();
			}
			else{
				throw new PathDoesNotExistException(name);
			}
		}
	}

	public final String result(){
		return path;
	}

	private String checkPath(String path, Directory dir){

		MyDrive md = MyDrive.getInstance();
		Directory rd = md.getRootdir();
		rd.getPermissions();
		String dirname = "";
		Integer c = 0;
		String auxname = path + "/";
		for(char ch : auxname.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				for (pt.tecnico.MyDrive.domain.File d : rd.getFileSet()){
					if(d.getName().equals(dirname)){
						if(d instanceof Directory ){
							return "absolute";
						}
					}
				}
				for (pt.tecnico.MyDrive.domain.File d2 : dir.getFileSet()){
					if(d2.getName().equals(dirname)){
						if(d2 instanceof Directory ){
							return "relative";
						}
					}
				}
				break;
			}
			else{
				dirname += ch;
			}
		}
		
		return "";
	}

	private Directory getDirByPath(String path, Directory dir){
		String dirname = "";
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				try{
					dir = (Directory) (dir.getDirByName(dirname));
					dirname="";
				}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }
			}
			else{
				dirname += ch;
			}
		}
		try{
			dir = (Directory) (dir.getDirByName(dirname));
		}catch (DirectoryDoesNotExistException | FileIsPlainFileException e) { System.err.println(e); }
		return dir;

	}

	private Session getSessionByToken(long token){
		MyDrive md = MyDrive.getInstance();

		for(Session s : md.getSessionSet()){
			if(s.getToken()==token){
				return s;
			}
		}
		throw new SessionDoesNotExistException(token);
	}
	
}


