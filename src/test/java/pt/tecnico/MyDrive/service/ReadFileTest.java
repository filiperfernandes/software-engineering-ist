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
//	
//	@Test(expected=FileDoesNotExistException.class)
//	public void fileDoesNotExistReadFile(){
//		ReadFileService file = new ReadFile(toke, "nomeFicheiro");
//		file.execute();
//		//Ficheiro nao existe
//	}
	
	
}
