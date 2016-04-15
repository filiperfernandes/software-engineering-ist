package pt.tecnico.MyDrive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.tecnico.MyDrive.Exception.*;
import pt.ist.fenixframework.Atomic;
import pt.tecnico.MyDrive.domain.User;
import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.MyDriveException;

	public abstract class MyDriveService {
	    protected static final Logger log = LogManager.getRootLogger();

	    @Atomic
	    public final void execute() throws MyDriveException {
	        dispatch();
	    }

	    static MyDrive getMyDrive() {
	        return MyDrive.getInstance();
	    }

	    static File getPerson(String fileName) throws FileDoesNotExistException {
	    	File f = null;
	    	//File f = getMyDrive().getFileByName(fileName);

	        if (f == null)
	            throw new FileDoesNotExistException(fileName);

	        return f;
	    }

	    protected abstract void dispatch() throws MyDriveException;
	
			public static boolean checkPermissionsRead(User user, User owner, String permissions){
		char ch1[] = permissions.toCharArray();
		String userPermissions = user.getMask();
		char ch2[] = userPermissions.toCharArray();

		if(owner.equals(user)){
			return true;	
		}
		else if (ch1[5]==ch2[5] && ch1[5]=='r'){
			return true;
		}
		else{
			throw new UserDoesNotHavePermissionsException();
		}

	}

	public static boolean checkPermissionsWrite(User user, User owner, String permissions){
		char ch1[] = permissions.toCharArray();
		String userPermissions = user.getMask();
		char ch2[] = userPermissions.toCharArray();

		if(owner.equals(user)){
			return true;	
		}
		else if (ch1[6]==ch2[6] && ch1[6]=='w'){
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
		else if (ch1[7]==ch2[7] && ch1[7]=='x'){
			return true;
		}
		else{
			throw new UserDoesNotHavePermissionsException();
		}
	}
	public static boolean checkPermissionsDelete(User user, User owner, String permissions){
		char ch1[] = permissions.toCharArray();
		String userPermissions = user.getMask();
		char ch2[] = userPermissions.toCharArray();

		if(owner.equals(user)){
			return true;	
		}
		else if (ch1[8]==ch2[8] && ch1[8]=='d'){
			return true;
		}
		else{
			throw new UserDoesNotHavePermissionsException();
		}
	}
	}


