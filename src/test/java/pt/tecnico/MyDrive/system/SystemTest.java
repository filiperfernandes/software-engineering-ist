package pt.tecnico.MyDrive.system;

import static org.junit.Assert.assertEquals;
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
		/*
		new Import(sh).execute(new String[] { "other.xml" } );
        new CreatePerson(sh).execute(new String[] { "Rui" } );
        new CreateContact(sh).execute(new String[] { "Rui", "SOS", "112" } );
        new List(sh).execute(new String[] { } );
        new RemoveContact(sh).execute(new String[] { "Xana", "Xico" } );
        new RemovePerson(sh).execute(new String[] { "Sofia" } );
        new Export(sh).execute(new String[] { } );
		*/
		}
}
