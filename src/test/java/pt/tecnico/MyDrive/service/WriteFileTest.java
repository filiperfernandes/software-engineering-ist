package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.FileIsDirectoryException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.Exception.UserDoesNotHavePermissionsException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.User;

public class WriteFileTest extends AbstractServiceTest{
	
	MyDrive md;


	@Override
	protected void populate() {
		md = MyDrive.getInstance();
		new User( "joao", "123", "Joao", "whatever");
		
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
	
	@Test(expected=UserDoesNotHavePermissionsException.class)
	public void DoNotHavePermissionsWriteFile(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
				
		CreateFileService file = new CreateFileService(log.result(), "test", "PlainFile", "ii");
		file.execute();
					
		LoginUserService log1 = new LoginUserService(md, "joao","123");
		log1.execute();
		
		ChangeDirectoryService dir = new ChangeDirectoryService("/home/root", log1.result());
		dir.execute();
								
		WriteFileService file1 = new WriteFileService("test", log1.result(),"Bota Fogo");
		file1.execute();
	}
	

}
