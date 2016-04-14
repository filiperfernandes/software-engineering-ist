package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.InvalidPasswordException;
import pt.tecnico.MyDrive.Exception.UsernameDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.User;
import pt.tecnico.MyDrive.MyDriveApplication;

public class LoginUserTest extends AbstractServiceTest {

	@Override
	protected void populate() {
//		MyDrive md = MyDrive.getInstance();
//		
//		new User("joao", "12345", "Joao", "/home/joao");
//		new Session(md, "joao", "12345");
//		
	}

	@Test(expected=InvalidPasswordException.class)
	public void invalidPasswordLoginUser(){
		
		
	}
	
	@Test(expected=UsernameDoesNotExistException.class)
	public void userNameDoesNotExistLoginUser(){
		
	}
	
	@Test
	public void userLogin(){
		long actual  = login("joao", "12345");
		
		assertTrue(actual instanceof long);
	}
	
	
	
	
	
	
}
