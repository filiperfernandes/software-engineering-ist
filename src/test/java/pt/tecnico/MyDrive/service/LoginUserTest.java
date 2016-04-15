package pt.tecnico.MyDrive.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.InvalidPasswordException;
import pt.tecnico.MyDrive.Exception.UsernameDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.User;

public class LoginUserTest extends AbstractServiceTest {

	MyDrive md;

	@Override
	protected void populate() {
		md = MyDrive.getInstance();
		User u = new User( "joao", "12345", "Joao", "lskdjgnfdkj");
//		
//		System.out.println(u.getUserByUsername("joao").getName());
	}

	@Test(expected=InvalidPasswordException.class)
	public void invalidPasswordLoginRoot(){
		LoginUserService log =  new LoginUserService(md, "root", "99999");
		log.execute();

	}
	
	@Test(expected=InvalidPasswordException.class)
	public void invalidPasswordLoginUser(){
		LoginUserService log =  new LoginUserService(md, "joao", "99999");
		log.execute();

	}

	@Test(expected=UsernameDoesNotExistException.class)
	public void userNameDoesNotExistLoginUser(){
		LoginUserService log =  new LoginUserService(md, "Carvalho", "12345");
		log.execute();
	}

	@Test
	public void rootLogin(){
		
		LoginUserService log =  new LoginUserService(md, "root", "***");
		log.execute();
		long a = log.result();
		
		assertNotNull(a);
	}
	
	@Test
	public void userLogin(){
		LoginUserService log =  new LoginUserService(md, "joao", "12345");
		log.execute();
		long a = log.result();

		assertNotNull(a);
	}
}
