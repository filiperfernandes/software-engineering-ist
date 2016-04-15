package pt.tecnico.MyDrive.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.MyDrive;

import pt.tecnico.MyDrive.service.dto.FileInfoDto;


public class ListDirectoryTest extends AbstractServiceTest {

	MyDrive md;


	@Override
	protected void populate() {
		// TODO Auto-generated method stub
		
	}

	@Test(expected=SessionDoesNotExistException.class)
	public void sessionDoesNotExistListDirectory(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		
		ListDirectoryService dir = new ListDirectoryService(345234324);
		dir.execute();
	}
	
	@Test
	public void ListDirectory(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
		
		ListDirectoryService dir = new ListDirectoryService(log.result());
		dir.execute();
		List<FileInfoDto> cs = dir.result();
	
		assertEquals(2, cs.size());
		assertEquals(".", cs.get(0).getName());
		assertEquals("..", cs.get(1).getName());
		assertEquals("root", cs.get(0).getOwner());
		assertEquals("root", cs.get(1).getOwner());
		assertEquals("rwxdr-x-", cs.get(1).getPermissions());

		
	}
	
	
	
	
}
