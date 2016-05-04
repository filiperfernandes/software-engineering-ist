package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.PathToBigException;
import pt.tecnico.MyDrive.Exception.PathDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;

public class ChangeDirectoryTest extends AbstractServiceTest {

	MyDrive md;

	@Override
	protected void populate() {
		md = MyDrive.getInstance();
	}

	private static String getPathBig(){
		Integer c = 0;
		String path = "";
		while(c<1025){
			path = path + "/";
			c++;
		}
		return path;
	}

	@Test(expected=PathDoesNotExistException.class)
	public void pathDoesNotExistChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService("/casa", token);
		dir.execute();
	}

	@Test(expected=PathToBigException.class)
	public void pathToBigChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService(getPathBig(), token);
		dir.execute();
	}

	@Test(expected=SessionDoesNotExistException.class)
	public void sessionDoesNotExistChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();

		ChangeDirectoryService dir = new ChangeDirectoryService("/home", 587432);
		dir.execute();
	}

	@Test
	public void pathDoesNotExistChangeDirecto(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService("/home", token);
		dir.execute();
		String actual = dir.result();

		assertEquals("/home", actual);
	}


	@Test
	public void pontoChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService(".", token);
		dir.execute();
		String actual = dir.result();

		assertEquals("/home/root", actual);
	}

	@Test
	public void doispontosChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService("..", token);
		dir.execute();
		String actual = dir.result();

		assertEquals("/home", actual);
	}

	@Test
	public void relativoChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService("..", token);
		dir.execute();

		dir = new ChangeDirectoryService("/root", token);
		dir.execute();
		String actual = dir.result();

		assertEquals("/home/root", actual);
	}

	@Test
	public void absoluteChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService("..", token);
		dir.execute();

		dir = new ChangeDirectoryService("/home/root", token);
		dir.execute();
		String actual = dir.result();

		assertEquals("/home/root", actual);
	}

	@Test
	public void barraChangeDirectory(){
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		long token = log.result();

		ChangeDirectoryService dir = new ChangeDirectoryService("/", token);
		dir.execute();

		dir = new ChangeDirectoryService("..", token);
		dir.execute();
		String actual = dir.result();

		assertEquals("/", actual);
	}


}