package pt.tecnico.MyDrive;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;

import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.SuperUser;


public class MyDriveApplication {
    //static final Logger log = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {
            System.out.println("*** Welcome to the MyDrive application! ***");
        try {
        	System.out.println("Hello");
                setup();

            } finally { FenixFramework.shutdown(); }
        }


@Atomic
public static void setup() {
	
	MyDrive md = MyDrive.getInstance();
	SuperUser sp = new SuperUser("root", "***", "Super User", "rwxdr-x-", "/home/root");

    
}}

