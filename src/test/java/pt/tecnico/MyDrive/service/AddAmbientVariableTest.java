package pt.tecnico.MyDrive.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.AmbientVariable;
import pt.tecnico.MyDrive.domain.AmbientVariable_Base;
import pt.tecnico.MyDrive.domain.MyDrive;

public class AddAmbientVariableTest extends AbstractServiceTest{

	MyDrive md;
	
	@Override
	protected void populate() {
		
	}

	
	
	@Test(expected=SessionDoesNotExistException.class)
	public void sessionDoesNotExistTest(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(54546545, "variavel", "2");
		test.execute();
	}
	
	@Test
	public void test01(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "variavel", "2");
		test.execute();
		
		TreeMap<String,String>  map = test.result();
		
		assertEquals(true, map.containsKey("variavel"));
		assertEquals("2", map.get("variavel"));

	}
	
	@Test
	public void test02(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "variavel", "2");
		test.execute();
		
		test = new AddAmbientVariableService(log.result(), "variavel", "2456");
		test.execute();

		TreeMap<String,String>  map = test.result();
		
		assertEquals(true, map.containsKey("variavel"));
		assertEquals("2456", map.get("variavel"));

	}
	
	@Test
	public void test03(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "v1", "2");
		test.execute();
		
		test = new AddAmbientVariableService(log.result(), "v2", "3");
		test.execute();
		
		test = new AddAmbientVariableService(log.result(), "v3", "4");
		test.execute();

		TreeMap<String,String>  map = test.result();

		assertEquals(true, map.containsKey("v3"));
		assertEquals("4", map.get("v3"));

		assertEquals(true, map.containsKey("v2"));
		assertEquals("3", map.get("v2"));

		assertEquals(true, map.containsKey("v1"));
		assertEquals("2", map.get("v1"));
	}
	
	@Test
	public void test04(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "v1", "2");
		test.execute();
		
		test = new AddAmbientVariableService(log.result(), "v2", "3");
		test.execute();
		
		test = new AddAmbientVariableService(log.result(), "v2", "6666");
		test.execute();
		
		test = new AddAmbientVariableService(log.result(), "v3", "4");
		test.execute();

		TreeMap<String,String>  map = test.result();

		assertEquals(true, map.containsKey("v3"));
		assertEquals("4", map.get("v3"));

		assertEquals(true, map.containsKey("v2"));
		assertEquals("6666", map.get("v2"));

		assertEquals(true, map.containsKey("v1"));
		assertEquals("2", map.get("v1"));

	}
	
}

