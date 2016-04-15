package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;

public class ReadFileTest extends AbstractServiceTest{



	@Override
	protected void populate() {
		// TODO Auto-generated method stub
		
	}

//	@Test(expected=SessionDoesNotExistException.class)
//	public void sessionDoesNotExistReadFile(){
//		ReadFileService file = new ReadFile(toke, "nomeFicheiro");
//		file.execute();
//		//Dá erro porque o token e de uma sessao que nao existe
//	}
//	
//	@Test(expected=FileDoesNotExistException.class)
//	public void fileDoesNotExistReadFile(){
//		ReadFileService file = new ReadFile(toke, "nomeFicheiro");
//		file.execute();
//		//Ficheiro nao existe
//	}
	
	
}
