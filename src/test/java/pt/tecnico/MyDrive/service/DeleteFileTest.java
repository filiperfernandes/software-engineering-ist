package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;

public class DeleteFileTest extends AbstractServiceTest{
	
	@Override
	protected void populate() {
		// TODO Auto-generated method stub
		
	}

//	@Test(expected=SessionDoesNotExistException.class)
//	public void sessionDoesNotExistDeleteFile(){
//		DeleteFileService del = new DeleteFileService(token, "nomeFicheiro");
//		del.execute();
//		//Token de uma sessao que nao existe
//		
//	}
//	
//	@Test(expected=FileDoesNotExistException.class)
//	public void fileDoesNotExistDeleteFile(){
//		
//		DeleteFileService del = new DeleteFileService(token, "nomeFicheiro");
//		del.execute();
//		//Ficheiro nao existe
//	}
	
	
	
}
