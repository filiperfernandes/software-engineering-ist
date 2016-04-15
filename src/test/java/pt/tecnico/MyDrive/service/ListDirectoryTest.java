package pt.tecnico.MyDrive.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.Exception.UserDoesNotHavePermissionsException;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.User;
import pt.tecnico.MyDrive.service.dto.FileInfoDto;


public class ListDirectoryTest extends AbstractServiceTest {

	MyDrive md;


	@Override
	protected void populate() {
		md = MyDrive.getInstance();
		new User( "joao", "123", "Joao", "whatever");
		
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
	
	@Test(expected=UserDoesNotHavePermissionsException.class)
	public void DoNotHavePermissionsListDirectory(){
		
		LoginUserService log = new LoginUserService(md, "root","***");
		log.execute();
				
		CreateFileService file = new CreateFileService(log.result(), "test", "Directory", null);
		file.execute();
					
		LoginUserService log1 = new LoginUserService(md, "joao","123");
		log1.execute();
		
		ChangeDirectoryService dir = new ChangeDirectoryService("/home/root/test", log1.result());
		dir.execute();
								
		ListDirectoryService file1 = new ListDirectoryService(log1.result());
		file1.execute();
	}
	
	
	
}
