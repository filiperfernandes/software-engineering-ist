package pt.tecnico.MyDrive.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.MyDrive;
//import pt.tecnico.MyDrive.domain.Mydrive; // Mockup
import pt.tecnico.MyDrive.service.*;
import pt.tecnico.MyDrive.service.dto.*;
import pt.tecnico.MyDrive.Exception.*;

@RunWith(JMockit.class)
public class IntegrationTest extends AbstractServiceTest {

	private static final List<String> names = new ArrayList<String>();
	private static final String p1 = "root", p2 = "guest", pw_r= "***";
	private static final String p3 = "", p4 = "Vultos", p5 = "Chamas";
	private long token=0;
	//private static final String importFile = "other.xml";
	//private static final int phone2 = 345678900, phone5 = 935667654;

	protected void populate() { // populate mockup
		names.add(p2);
		names.add(p4);
	}

	@Test
	public void success() throws Exception {

		LoginUserService ls = new LoginUserService(p1,pw_r);
		ls.execute();
		token = ls.result();
		
		new CreateFileService(token,"Casa","PlainFile", "Data");

		CreateFileService file = new CreateFileService(token, "nomeFicheiro", "Directory", null);
		file.execute();

		Directory actual = (Directory) file.result();
		assertEquals("nomeFicheiro", actual.getName());

		ChangeDirectoryService dir = new ChangeDirectoryService("/", token);
		dir.execute();

		dir = new ChangeDirectoryService("..", token);
		dir.execute();
		String actual_dir = dir.result();

		assertEquals("/", actual_dir);

		CreateFileService file2 = new CreateFileService(token , "FicheiroApaga", "PlainFile", "Conteudo null se for um dir");
		file2.execute();


		DeleteFileService del = new DeleteFileService(token , "FicheiroApaga");
		del.execute();

//		new MockUp<MyDrive>() {
//			@Mock
//			int getPhoneNumberByContact(String p2) { return phone2; }
//		};
//		FindPhoneNumberService pn = new FindPhoneNumberService(p2);
//		pn.execute();
//		assertEquals(pn.result(), phone2);
	}
}
