package pt.tecnico.MyDrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.MyDrive.Exception.InvalidStringException;
import pt.tecnico.MyDrive.Exception.InvalidTypeException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;

public class CreateFileTest extends AbstractServiceTest{





	@Override
	protected void populate() {
		// TODO Auto-generated method stub
		
	}

//	@Test(expected=SessionDoesNotExistException.class)
//	public void sessionDoesNotExistCreateFile(){
//		CreateFileService file = new CreateFile(token, "nomeFicheiro", "tipoFicheiro", "Conteudo null se for um dir");
//		file.execute();
//		//Token vai dar excepcao
//	}
//	
//	@Test(expected=InvalidStringException.class)
//	public void invalidStringChangeDirectory(){
//		CreateFileService file = new CreateFile(token, "nomeFicheiro", "tipoFicheiro", "Conteudo null se for um dir");
//		file.execute();
//		//Nome da excepcao
//	}
//	
//	@Test(expected=InvalidTypeException.class)
//	public void invalidTypeChangeDirectory(){
//		CreateFileService file = new CreateFile(token, "nomeFicheiro", "tipoFicheiro", "Conteudo null se for um dir");
//		file.execute();
//		//Tipo de ficheiro da excepcao
//	}
	
}
