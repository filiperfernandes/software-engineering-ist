package pt.tecnico.MyDrive.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;
import pt.tecnico.MyDrive.domain.AmbientVariable;
import pt.tecnico.MyDrive.domain.MyDrive;

//public class AddAmbientVariableTest extends AbstractServiceTest{
//
//	MyDrive md;
//	
//	@Override
//	protected void populate() {
//		
//	}
//
//	@Test(expected=NullArgumentException.class)
//	public void nullArgumentsTest01(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), null, "2");
//		test.execute();
//	}
//	
//	@Test(expected=NullArgumentException.class)
//	public void nullArgumentsTest02(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "variavel", null);
//		test.execute();
//	}
//	
//	@Test(expected=SessionDoesNotExistException.class)
//	public void sessionDoesNotExistTest(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		AddAmbientVariableService test = new AddAmbientVariableService(54546545, "variavel", "2");
//		test.execute();
//	}
//	
//	@Test
//	public void test01(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "variavel", "2");
//		test.execute();
//		
//		List<AmbientVariable>  list = test.result();
//		
//		assertEquals(log.result(), list.get(0).getSession().getToken());
//		assertEquals("variavel", list.get(0).getName());
//		assertEquals("2", list.get(0).getValue());
//	}
//	
//	@Test
//	public void test02(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "variavel", "2");
//		test.execute();
//		
//		test = new AddAmbientVariableService(log.result(), "variavel", "2456");
//		test.execute();
//		List<AmbientVariable>  list = test.result();
//		
//		assertEquals(log.result(), list.get(0).getSession().getToken());
//		assertEquals("variavel", list.get(0).getName());
//		assertEquals("2456", list.get(0).getValue());
//	}
//	
//	@Test
//	public void test03(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "v1", "2");
//		test.execute();
//		
//		test = new AddAmbientVariableService(log.result(), "v2", "3");
//		test.execute();
//		
//		test = new AddAmbientVariableService(log.result(), "v3", "4");
//		test.execute();
//		List<AmbientVariable>  list = test.result();
//		
//		//A lista fica ordenada ao contrario, o ultimo a ser criado
//		//fica na primeira posicao (get(0))
//		
//		assertEquals(log.result(), list.get(0).getSession().getToken());
//		assertEquals("v3", list.get(0).getName());
//		assertEquals("4", list.get(0).getValue());
//		
//		assertEquals(log.result(), list.get(1).getSession().getToken());
//		assertEquals("v2", list.get(1).getName());
//		assertEquals("3", list.get(1).getValue());
//		
//		assertEquals(log.result(), list.get(2).getSession().getToken());
//		assertEquals("v1", list.get(2).getName());
//		assertEquals("2", list.get(2).getValue());
//	}
//	
//	@Test
//	public void test04(){
//		
//		LoginUserService log = new LoginUserService("root","***");
//		log.execute();
//		
//		AddAmbientVariableService test = new AddAmbientVariableService(log.result(), "v1", "2");
//		test.execute();
//		
//		test = new AddAmbientVariableService(log.result(), "v2", "3");
//		test.execute();
//		
//		test = new AddAmbientVariableService(log.result(), "v2", "6666");
//		test.execute();
//		
//		test = new AddAmbientVariableService(log.result(), "v3", "4");
//		test.execute();
//		List<AmbientVariable>  list = test.result();
//		
//		//A lista fica ordenada ao contrario, o ultimo a ser criado
//		//fica na primeira posicao (get(0))
//		
//		assertEquals(log.result(), list.get(0).getSession().getToken());
//		assertEquals("v3", list.get(0).getName());
//		assertEquals("4", list.get(0).getValue());
//		
//		assertEquals(log.result(), list.get(1).getSession().getToken());
//		assertEquals("v2", list.get(1).getName());
//		assertEquals("6666", list.get(1).getValue());
//		
//		assertEquals(log.result(), list.get(2).getSession().getToken());
//		assertEquals("v1", list.get(2).getName());
//		assertEquals("2", list.get(2).getValue());
//	}
//	
//}
