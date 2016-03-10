package pt.tecnico.MyDrive.domain;

import java.io.File;

import org.jdom2.Element;
import org.jdom2.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;

public class MyDrive extends MyDrive_Base {
    
    public MyDrive() {
        super();
    }
    
public static MyDrive getInstance() {
        MyDrive md = FenixFramework.getDomainRoot().getMydrive();
        if (md != null)
		return md;
	    
        return new MyDrive();
}
    
}
