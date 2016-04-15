package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.Exception.UserDoesNotHavePermissionsException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.User;
import pt.tecnico.MyDrive.service.dto.FileInfoDto;

public class DeleteFileTest extends AbstractServiceTest{
	
	MyDrive md;
	
	@Override
	protected void populate() {
		md = MyDrive.getInstance();
		new User( "joao", "123", "Joao", "whatever");
		
	}

	@Test(expected=SessionDoesNotExistException.class)
	public void sessionDoesNotExistDeleteFile(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		
		CreateFileService file = new CreateFileService(log.result() , "nomeFicheiro", "PlainFile", "Conteudo null se for um dir");
		file.execute();
		
		
		DeleteFileService del = new DeleteFileService(122321, "nomeFicheiro");
		del.execute();
		
	}
	
	@Test(expected=FileDoesNotExistException.class)
	public void fileDoesNotExistDeleteFile(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();
		
		CreateFileService file = new CreateFileService(token , "nomeFicheiro", "PlainFile", "Conteudo null se for um dir");
		file.execute();
		
		
		DeleteFileService del = new DeleteFileService(token , "NaoApaga");
		del.execute();
	}
	
	@Test(expected=FileDoesNotExistException.class)
	public void fileDoesNotExistDeleteFile1(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();
		
		CreateFileService file = new CreateFileService(token , "nomeFicheiro", "Directory", "Conteudo null se for um dir");
		file.execute();
		
		
		DeleteFileService del = new DeleteFileService(token , "NaoApaga");
		del.execute();
	}
	
	@Test
	public void DeleteFile(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();
		
		CreateFileService file = new CreateFileService(token , "FicheiroApaga", "PlainFile", "Conteudo null se for um dir");
		file.execute();
		
		
		DeleteFileService del = new DeleteFileService(token , "FicheiroApaga");
		del.execute();
		
		ListDirectoryService dir = new ListDirectoryService(log.result());
		dir.execute();
		List<FileInfoDto> cs = dir.result();
		
		assertEquals(2, cs.size());
		assertEquals(".", cs.get(0).getName());
		assertEquals("..", cs.get(1).getName());
	}
	
	@Test
	public void DeleteFile1(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();
		
		CreateFileService file = new CreateFileService(token , "DirectoryApaga", "Directory", "Conteudo null se for um dir");
		file.execute();
		
		
		DeleteFileService del = new DeleteFileService(token , "DirectoryApaga");
		del.execute();
		
		ListDirectoryService dir = new ListDirectoryService(log.result());
		dir.execute();
		List<FileInfoDto> cs = dir.result();
		
		assertEquals(2, cs.size());
		assertEquals(".", cs.get(0).getName());
		assertEquals("..", cs.get(1).getName());
	}
	
	@Test(expected=UserDoesNotHavePermissionsException.class)
	public void DoNotHavePermissionsDeleteFile(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
				
		CreateFileService file = new CreateFileService(log.result(),"test", "Directory", null);
		file.execute();
					
		LoginUserService log1 = new LoginUserService(md, "joao","123");
		log1.execute();
		
		ChangeDirectoryService dir = new ChangeDirectoryService("/home/root", log1.result());
		dir.execute();
								
		DeleteFileService del = new DeleteFileService(log1.result(), "test");
		del.execute();
	}
	
	@Test(expected=UserDoesNotHavePermissionsException.class)
	public void DoNotHavePermissionsDeleteFile1(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
				
		CreateFileService file = new CreateFileService(log.result(), "test", "PlainFile", "HII");
		file.execute();
					
		LoginUserService log1 = new LoginUserService(md, "joao","123");
		log1.execute();
		
		ChangeDirectoryService dir = new ChangeDirectoryService("/home/root", log1.result());
		dir.execute();
								
		DeleteFileService del = new DeleteFileService(log1.result(), "test");
		del.execute();
	}
	
}
