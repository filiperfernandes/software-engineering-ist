package pt.tecnico.MyDrive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
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

	    static File getFile(String fileName) throws FileDoesNotExistException {
	    	File f = null;
	    	//File f = getMyDrive().getFileByName(fileName);

	        if (f == null)
	            throw new FileDoesNotExistException(fileName);

	        return f;
	    }

	    protected abstract void dispatch() throws MyDriveException;
	
	}

