package pt.tecnico.MyDrive;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.joda.time.DateTime;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
//import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.MyDrive;

public class MyDriveApplication {
	//static final Logger log = LogManager.getRootLogger();


	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {

			System.out.println("Hello");
			setup();
			for (String s: args) xmlScan(new File(s));
			DateTime date = new DateTime();


			//File c = new File(md.getCounter(), "c", "/home/root", "/home/root", date, "rwxdr-x-");
			//md.setCounter(md.getCounter()+1);

		} finally { FenixFramework.shutdown(); }
	}


	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();

		//new SuperUser();

		//createPlainFile("root", "/home/root/test", "/home/root", "rwxdr-test", "Hello World!");
		//r.setDimension(r.getDimension()+1);

		//new User("Filipe", "test", "Filipe Fernandes");

	}


	@Atomic
	public static void xmlScan(File file) {

		MyDrive md = MyDrive.getInstance();

		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document)builder.build(file);
			md.xmlImport(document.getRootElement());
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}



}

/*public static void createPlainFile(String owner, String name, String pathToFile, String permissions, String data){
	@Atomic
		MyDrive md = MyDrive.getInstance();
		DateTime date = new DateTime();

		//new PlainFile(md.getCnt(), owner, name, pathToFile, date, "rwxdr-test", "Hello World!");
		//Directory f = (Directory)md.getFileByPath(pathToFile);



	}*/

/*
	@Atomic
	public static void xmlScan(File file) {
		MyDrive md = MyDrive.getInstance();
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document)builder.build(file);
			md.xmlImport(document.getRootElement());
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}*/


