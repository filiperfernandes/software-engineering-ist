package pt.tecnico.MyDrive.system;

import org.junit.Test;

import pt.tecnico.MyDrive.presentation.*;
import pt.tecnico.MyDrive.service.AbstractServiceTest;

public class SystemTest extends AbstractServiceTest {

	private MdShell sh;

	protected void populate() {
		sh = new MdShell();
	}
	
	@Test
	public void success() {
		
		new Login(sh).execute(new String[] { "root", "***" } );
		new List(sh).execute(new String[] {} );
		new ChangeWorkingDirectory(sh).execute(new String[] { ".." } );							
	}
}