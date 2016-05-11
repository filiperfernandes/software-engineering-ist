package pt.tecnico.MyDrive.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.AmbientVariable;
import pt.tecnico.MyDrive.domain.MyDrive;

public class AddAmbientVariableTest extends AbstractServiceTest{

	MyDrive md;
	
	@Override
	protected void populate() {
		
	}

	@Test(expected=NullArgumentException.class)
	public void nullArgumentsTest01(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), null, "2");
		test.execute();
	}
	
	@Test(expected=NullArgumentException.class)
	public void nullArgumentsTest02(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "variavel", null);
		test.execute();
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
		
		TreeMap<String,String>  list = test.result();
		
		assertEquals("2", list.get("variavel"));
	}
	
	@Test
	public void test02(){
		
		LoginUserService log = new LoginUserService("root","***");
		log.execute();
		
		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "variavel", "2");
		test.execute();
		
		test = new AddAmbientVariableService(log.result(), "variavel", "2456");
		test.execute();
		TreeMap<String,String>  list = test.result();
		
		assertEquals("2456", list.get("variavel"));
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
		TreeMap<String,String>  list = test.result();
		
		
		assertEquals("2", list.get("v1"));
		
		assertEquals("3", list.get("v2"));
		
		assertEquals("4", list.get("v3"));
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
		TreeMap<String,String>  list = test.result();
		
		
		assertEquals("2", list.get("v1"));
		
		assertEquals("6666", list.get("v2"));
		
		assertEquals("4", list.get("v3"));
	}
	
}
