package pt.tecnico.MyDrive.domain;


import org.jdom2.Element;
import org.jdom2.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.Exception.*;

import pt.ist.fenixframework.FenixFramework;

public class MyDrive extends MyDrive_Base {

	public static MyDrive getInstance() {
		MyDrive md = FenixFramework.getDomainRoot().getMydrive();
		if (md != null){
			return md;}
		return new MyDrive();
	}

	private MyDrive() {
		setRoot(FenixFramework.getDomainRoot());
		setCounter(0);
		new SuperUser();
	}

	public Integer getCnt(){
		Integer i = getCounter();
		setCounter(i+1);
		return i;
	}
	

	


}
