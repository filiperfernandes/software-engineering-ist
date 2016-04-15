package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.Exception.UserDoesNotHavePermissionsException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.User;

public class ReadFileTest extends AbstractServiceTest{

	MyDrive md;

	@Override
	protected void populate() {
		md = MyDrive.getInstance();
		new User( "joao", "123", "Joao", "whatever");
				
	}

	@Test(expected=SessionDoesNotExistException.class)
	public void sessionDoesNotExistReadFile(){
		LoginUserService log =  new LoginUserService(md, "root", "***");
		log.execute();
		
		ReadFileService file = new ReadFileService("nomeFicheiro", 45656456);
		file.execute();
	}
	
	@Test(expected=FileDoesNotExistException.class)
	public void fileDoesNotExistReadFile(){
		LoginUserService log =  new LoginUserService(md, "root", "***");
		log.execute();
		long token = log.result();
		
		CreateFileService plain = new CreateFileService(token, "File", "PlainFile", "Ola test");
		plain.execute();
		
		ReadFileService file = new ReadFileService("Name",log.result());
		file.execute();
	}
	
	@Test
	public void ReadFile(){
		LoginUserService log =  new LoginUserService(md, "root", "***");
		log.execute();
		long token = log.result();
		
		CreateFileService plain = new CreateFileService(token, "NewFile", "PlainFile", "Oi test");
		plain.execute();
		
		ReadFileService file = new ReadFileService("NewFile",log.result());
		file.execute();
		
		assertEquals("Oi test", file.result());
	}
	
	@Test(expected=UserDoesNotHavePermissionsException.class)
	public void DoNotHavePermissionsReadFile(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
				
		CreateFileService file = new CreateFileService(log.result(), "test", "PlainFile", "ii");
		file.execute();
					
		LoginUserService log1 = new LoginUserService(md, "joao","123");
		log1.execute();
		
		ChangeDirectoryService dir = new ChangeDirectoryService("/home/root", log1.result());
		dir.execute();
								
		ReadFileService file1 = new ReadFileService("test", log1.result());
		file1.execute();
	}
	
	
}
