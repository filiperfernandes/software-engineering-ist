package pt.tecnico.MyDrive;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.joda.time.DateTime;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.domain.User;

public class MyDriveApplication {
	//static final Logger log = LogManager.getRootLogger();


	public static void main(String[] args) throws IOException {
		System.out.println("*** Welcome to the MyDrive application! ***");
		try {

			System.out.println("Hello");
			setup();
			for (String s: args) xmlScan(new File(s));
			
			addData();
			xmlPrint();
			

		} finally { FenixFramework.shutdown(); }
	}

	
	//Initialize MyDrive
	@Atomic
	public static void setup() {

		MyDrive md = MyDrive.getInstance();

	}
	
	
	//Add data to database
	@Atomic
	public static void addData(){
		MyDrive md = MyDrive.getInstance();
		
		DateTime date = new DateTime();
		
		User u= new User("filiperfernandes", "fPW", "Filipe");
		
		PlainFile p = new PlainFile(md.getCnt(), "Hello", date, "rwxdr-x-", "HelloWorld"); 
		
		//u.addFile(p);
		
		
	}
	
	//Not sure if needed
	@Atomic
	public static Directory getHome(){
		MyDrive md = MyDrive.getInstance();
		Directory home = (Directory) md.getRootdir().getFileByName("home");
		System.out.println("APP O HOME E:" + home);
		return home;
	}
	
	//Export XML
	@Atomic
    public static void xmlPrint() {
	Document doc = MyDrive.getInstance().xmlExport();
	XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
	try { xmlOutput.output(doc, new PrintStream(System.out));
	} catch (IOException e) { System.out.println(e); }
    }
	
	//Import XML
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