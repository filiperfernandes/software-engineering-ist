package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.InvalidStringException;
import pt.tecnico.MyDrive.Exception.InvalidTypeException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.Exception.UserDoesNotHavePermissionsException;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.Link;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.User;

public class CreateFileTest extends AbstractServiceTest{

	MyDrive md;

	@Override
	protected void populate() {
		md = MyDrive.getInstance();
		new User( "joao", "123456789", "Joao", "whatever","");

	}

	@Test(expected=SessionDoesNotExistException.class)
	public void sessionDoesNotExistCreateFile(){

		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();

		CreateFileService file = new CreateFileService(2354235 , "nomeFicheiro", "PlainFile", "Conteudo null se for um dir");
		file.execute();
	}

	@Test(expected=InvalidStringException.class)
	public void invalidStringCreateFile(){

		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();

		CreateFileService file = new CreateFileService(log.result(), "nomeFicheiro/.", "Directory", null);
		file.execute();
	}

	@Test(expected=InvalidTypeException.class)
	public void invalidTypeCreateFile(){

		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();

		CreateFileService file = new CreateFileService(log.result(), "nomeFicheiro", "tipoFicheiro", null);
		file.execute();
	}


	@Test
	public void createFileDirectory(){

		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();

		CreateFileService file = new CreateFileService(log.result(), "nomeFicheiro", "Directory", null);
		file.execute();
		
		Directory actual = (Directory) file.result();


		assertEquals("nomeFicheiro", actual.getName());

	}

	@Test
	public void createFilePlainFile(){

		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();

		CreateFileService file = new CreateFileService(log.result(), "nomePlainFile", "PlainFile", "Teste ola");
		file.execute();
		PlainFile actual = (PlainFile) file.result();

		assertEquals("nomePlainFile", actual.getName());

	}
	
	@Test(expected=UserDoesNotHavePermissionsException.class)
	public void DoNotHavePermissionsCreateFile(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		
		CreateFileService file = new CreateFileService(log.result(), "test", "Directory", null);
		file.execute();
		
		LoginUserService log1 = new LoginUserService(md, "joao","123456789");
		log1.execute();
		
		ChangeDirectoryService dir = new ChangeDirectoryService("/home/root/test", log1.result());
		dir.execute();
		
		CreateFileService file1 = new CreateFileService(log1.result(), "test1", "PlainFile", "iiii");
		file1.execute();
	}
	
	@Test
	public void createFileLink(){

		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		
		CreateFileService file = new CreateFileService(log.result(), "testPlainFile", "PlainFile", "Teste ola");
		file.execute();

		CreateFileService file1 = new CreateFileService(log.result(), "newLink", "Link", "/home/root/testPlainFile");
		file1.execute();
		Link actual = (Link) file1.result();
		

		assertEquals("newLink", actual.getName());
		assertEquals("/home/root/testPlainFile", actual.getData());
		
	}


}
