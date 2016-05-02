package pt.tecnico.MyDrive.domain;
import org.joda.time.DateTime;
import pt.tecnico.MyDrive.Exception.*;

import pt.tecnico.MyDrive.domain.Session;

public class File extends File_Base {


	protected File() {}

	public File(Integer id, String name,  String permissions) {
		super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		setPermissions(permissions);


	}


	public String getPath(){
		String path = "";
		File f = this;
		while(!((f.getName()).equals("/"))){
			path = "/" + f.getName() + path;
			//	System.out.println(f.getName());
			f = f.getDirectory();
		}
		return path;
	}

	public static boolean fileNameCheck(String name){
		for(char ch : name.toCharArray()){
			if(ch == '/' || ch == '.'){
				return false;
			}
		}
		return true;
	}


	public void remove(){
		this.remove();
		this.setUser(null);
		this.setDirectory(null);
		deleteDomainObject();

	}

	public static void deleteFile(String name, long tok) {

		Session session = Session.getSessionByToken(tok);
		Directory dir = session.getCurrentdir();
		System.out.println(dir.getName());

		try{
			pt.tecnico.MyDrive.domain.File file = dir.getFileByName(name);
			checkPermissionsDelete(session.getUser(), file.getUser(), file.getPermissions());
			if (file instanceof Directory) {
				((Directory) file).removeDir();				
			}
			else if (file instanceof PlainFile) {
				((PlainFile) file).removePlainFile();
			}
		}catch(FileDoesNotExistException | FileIsDirectoryException | UserDoesNotHavePermissionsException e) { System.err.println(e); }
	}


	public static boolean checkPermissionsDelete(User user, User owner, String permissions){
		char ch1[] = permissions.toCharArray();
		String userPermissions = user.getMask();
		char ch2[] = userPermissions.toCharArray();

		if(owner.equals(user)){
			return true;	
		}
		else if (ch1[7]==ch2[7] && ch1[7]=='d'){
			return true;
		}
		else{
			throw new UserDoesNotHavePermissionsException();
		}

	}

	public static void writeFile(String name, long tok,String content) {

		Session session = Session.getSessionByToken(tok);
		Directory dir = session.getCurrentdir() ;

		try{
			PlainFile file = ((PlainFile) (dir.getPlainfileByName(name)));
			checkPermissionsWrite(session.getUser(), file.getUser(), file.getPermissions());
			file.setData(content);
		}catch(FileDoesNotExistException | FileIsDirectoryException | UserDoesNotHavePermissionsException e) { System.err.println(e); }
	}

	public static boolean checkPermissionsWrite(User user, User owner, String permissions){
		char ch1[] = permissions.toCharArray();
		String userPermissions = user.getMask();
		char ch2[] = userPermissions.toCharArray();

		if(owner.equals(user)){
			return true;	
		}
		else if (ch1[5]==ch2[5] && ch1[5]=='w'){
			return true;
		}
		else{
			throw new UserDoesNotHavePermissionsException();}

	}

	public static boolean checkPermissionsRead(User user, User owner, String permissions){
		char ch1[] = permissions.toCharArray();
		String userPermissions = user.getMask();
		char ch2[] = userPermissions.toCharArray();

		if(owner.equals(user)){
			return true;	
		}
		else if (ch1[4]==ch2[4] && ch1[4]=='r'){
			return true;
		}
		else{
			throw new UserDoesNotHavePermissionsException();
		}

	}



	public static boolean checkPermissionsExecute(User user, User owner, String permissions){
		char ch1[] = permissions.toCharArray();
		String userPermissions = user.getMask();
		char ch2[] = userPermissions.toCharArray();

		if(owner.equals(user)){
			return true;	
		}
		else if (ch1[6]==ch2[6] && ch1[6]=='x'){
			return true;
		}
		else{
			throw new UserDoesNotHavePermissionsException();
		}
	}
}



