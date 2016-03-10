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

		} finally { FenixFramework.shutdown(); }
	}


	@Atomic
	public static void setup() {
		
		MyDrive md = MyDrive.getInstance();
		DateTime date = new DateTime();
		Directory primary = new Directory(0, "root", "/", "/", date, "rwxdr-x-", 2, ". ..");
		Directory rd = new Directory(1, "root", "/home/root", "/home/root", date, "rwxdr-x-", 2, ". .. ola");
		
		File a = new File(1, "root", "/home/root", "/home/root", date, "rwxdr-x-");
		SuperUser su = new SuperUser();

		PlainFile pf = new PlainFile(3,"root", "test", "/home/root/test", date, "rwxdr-test", "Hello World!");
		
		User u = new User("filiperfernandes", "test", "Filipe Fernandes", "rwxdr-x-", "/home/Filipe");
		
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







}
