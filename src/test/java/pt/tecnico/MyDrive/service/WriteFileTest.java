package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.FileIsDirectoryException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;

public class WriteFileTest extends AbstractServiceTest{
	
	MyDrive md;


	@Override
	protected void populate() {
		md = MyDrive.getInstance();
		
	}

	
	@Test(expected=SessionDoesNotExistException.class)
	public void sessionDoesNotExistWriteFile(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		//long token = log.result();
		WriteFileService file = new WriteFileService("nomeFicheiro", 54546,"Conteudo");
		file.execute();
		//Token nao existe
	}
	
	@Test(expected=FileDoesNotExistException.class)
	public void fileDoesNotExistWriteFile(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();
		WriteFileService file = new WriteFileService("nomeFicheiro", token,"Conteudo");
		file.execute();
		//Ficheiro nao existe
	}
	
	@Test(expected=FileIsDirectoryException.class)
	public void fileIsDirectoryWriteFile(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();
		ChangeDirectoryService dir = new ChangeDirectoryService("..", token);
		dir.execute();
		WriteFileService file = new WriteFileService("root", token,"Conteudo");
		file.execute();
		//Ficheiro nao existe
	}
	
	@Test
	public void WriteFile(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();
		
		CreateFileService dir = new CreateFileService(token, "test", "PlainFile", "Conteudo Test");
		dir.execute();
		
		WriteFileService file = new WriteFileService("test", token,"Bota Fogo");
		file.execute();
		
		assertEquals("Bota Fogo", file.result().getData());
	}
	

}
