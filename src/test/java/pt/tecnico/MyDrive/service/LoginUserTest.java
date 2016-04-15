package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.InvalidPasswordException;
import pt.tecnico.MyDrive.Exception.UsernameDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.User;
import pt.tecnico.MyDrive.MyDriveApplication;
import pt.tecnico.MyDrive.service.LoginUserService;

public class LoginUserTest extends AbstractServiceTest {

	@Override
	protected void populate() {
		MyDrive md = MyDrive.getInstance();
		
		new User("joao", "12345", "Joao", "/home/joao");
		
	}

//	@Test(expected=InvalidPasswordException.class)
//	public void invalidPasswordLoginUser(){
//		LoginUserService log =  new LoginUserService("joao", "99999");
//		log.execute();
//		
//	}
//	
//	@Test(expected=UsernameDoesNotExistException.class)
//	public void userNameDoesNotExistLoginUser(){
//		LoginUserService log =  new LoginUserService("Carvalho", "12345");
//		log.execute();
//	}
//	
//	@Test
//	public void userLogin(){
//		LoginUserService log =  new LoginUserService("joao", "12345");
//		log.execute();
//		long a = log.result();
//		
//		assertEquals("", a);
//	}
	
	
	
	
}
