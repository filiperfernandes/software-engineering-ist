package pt.tecnico.MyDrive;

import java.io.IOException;

import org.joda.time.DateTime;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.File;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.SuperUser;
import pt.tecnico.MyDrive.domain.User;
import pt.tecnico.MyDrive.domain.PlainFile;

public class MyDriveApplication {
	//static final Logger log = LogManager.getRootLogger();

	
	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {

			System.out.println("Hello");
			setup();
			test1();
			DateTime date = new DateTime();


			//File c = new File(md.getCounter(), "c", "/home/root", "/home/root", date, "rwxdr-x-");
			//md.setCounter(md.getCounter()+1);

		} finally { FenixFramework.shutdown(); }
	}


	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();

		DateTime date = new DateTime();
		
		File a = new File(md.getCnt(), "a", "/home/root", "/home/root", date, "rwxdr-x-");
		

		File b = new File(md.getCnt(), "b", "/home/root", "/home/root", date, "rwxdr-x-");

		Directory x = new Directory(md.getCnt(), "x", "/home/root", "/home/root", date, "rwxdr-x-", 2, ". ..");
		
		
		SuperUser su = new SuperUser();
		User u = new User("filiperfernandes", "test", "Filipe Fernandes", "rwxdr-x-", "/home/Filipe");

		PlainFile pf = new PlainFile(md.getCnt(),"plainFile", "test", "/home/root/test", date, "rwxdr-test", "Hello World!");



	}

	@Atomic
	public static void test1() {
		
		MyDrive md = MyDrive.getInstance();

		DateTime date = new DateTime();

		File c = new File(md.getCnt(), "c", "/home/root", "/home/root", date, "rwxdr-x-");
	}
}
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


