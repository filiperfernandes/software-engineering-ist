package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.FileDoesNotExistException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;

public class WriteFileTest extends AbstractServiceTest{

	@Override
	protected void populate() {
		// TODO Auto-generated method stub
		
	}

	
//	@Test(expected=SessionDoesNotExistException.class)
//	public void sessionDoesNotExistWriteFile(){
//		WriteFileService file = new WriteFileService(token, "nomeFicheiro","Conteudo");
//		file.execute();
//		//Token nao existe
//	}
//	
//	@Test(expected=FileDoesNotExistException.class)
//	public void fileDoesNotExistWriteFile(){
//		WriteFileService file = new WriteFileService(token, "nomeFicheiro","Conteudo");
//		file.execute();
//		//Ficheiro nao existe
//	}
	
}
