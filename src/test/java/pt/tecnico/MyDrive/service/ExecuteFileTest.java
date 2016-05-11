package pt.tecnico.MyDrive.service;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;

public class ExecuteFileTest extends AbstractServiceTest{

	MyDrive md;
	
	@Override
	protected void populate() {

	}
	
	@Test(expected=NullArgumentException.class)
	public void nullArgumentTest01(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		String[] a = {"a","ola"};
		
		ExecuteFileService ex = new ExecuteFileService(log.result(), null , a);
		ex.execute();
		
	}
	
	
//	@Test(expected=NullArgumentException.class)
//	public void nullArgumentTest02(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		ExecuteFileService ex = new ExecuteFileService(log.result(), "/home/root" , null);
//		ex.execute();
//		
//	}
	
	@Test(expected=SessionDoesNotExistException.class)
	public void nullArgumentTest03(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		String[] a = {"a","ola"};
		
		ExecuteFileService ex = new ExecuteFileService(545654612, "/home/root" , a);
		ex.execute();
		
	}
	
	@Test(expected=FileDoesNotExistException.class)
	public void nullArgumentTest04(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		String[] a = {"a","ola"};
		
		ExecuteFileService ex = new ExecuteFileService(log.result(), "/home/root/text" , a);
		ex.execute();
	}
	
	
//	@Test
//	public void executeFileTest(){
//		
//		LoginUserService log = new LoginUserService(md, "root","***");
//		log.execute();
//		
//		CreateFileService file = new CreateFileService(log.result(), "nomeApp", "App", "pt.tecnico.Mydrive.domain.App.Hello");
//		file.execute();
//		String[] a = {"a","ola"};
//		
//		ExecuteFileService ex = new ExecuteFileService(log.result(), "/home/root/nomeApp" , a);
//		ex.execute();
//		
//	}
}
