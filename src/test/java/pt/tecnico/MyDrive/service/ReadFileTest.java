package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;

public class ReadFileTest extends AbstractServiceTest{

	MyDrive md;

	@Override
	protected void populate() {
				
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
	
	
}
