package pt.tecnico.MyDrive.domain;


import org.jdom2.Document;
import org.jdom2.Element;

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

	public User getUserByUsername(String username) {
		for (User user : getUserSet()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}



	public void xmlImport(Element element) {

		MyDrive md = MyDrive.getInstance();

		//Import users
		for (Element node: element.getChildren("user")) {
			String username = node.getAttribute("username").getValue();
			User user = getUserByUsername(username);

			if (user == null){ // Does not exist
				user = new User(this, username);
				System.out.println("adicionou "+user);
			}

			user.xmlImport(node);
		}

		//Import directories
		for (Element node: element.getChildren("dir")) {
			String id = node.getAttribute("id").getValue();
			Directory dir = (Directory) (md.getRootdir()).getFileByID(id);// falta criar funcao getPersonByName(name)

			if (dir == null){ // Does not exist
				dir = new Directory(this, id);
			}

			dir.xmlImport(node);// deve ser isto, os de baixo nao interessa

		}

	}

	public Document xmlExport() {

		MyDrive md = MyDrive.getInstance();
		Element element = new Element("MyDrive");
		Document doc = new Document(element);
		System.out.println("Vai entrar no procusar users");

		//Export Users
		for (User u: getUserSet()){
			System.out.println("User: "+ u.getName());
			element.addContent(u.xmlExport());
		}

		//Export Directories
		for (User u: getUserSet()){
			for (File f: u.getFileSet()){
				if(f instanceof Directory){
					System.out.println("File: "+ f.getName());
					element.addContent(((Directory) f).xmlExport());
				}
			}
		}

		//ExportPlainFiles
		for (User u1: getUserSet()){
			for (File f1: u1.getFileSet()){
				if(f1 instanceof PlainFile){
					System.out.println("File: "+ f1.getName());
					element.addContent(((PlainFile) f1).xmlExport());
				}
			}
		}

		return doc;
	}


}

/*
	public File getFileByName(String name){
		for (File file: getRootdir().getFileSet()){
			if(file.getName().equals(name)){
				return file;
			}
		}
		System.out.println("returning null");
		return null;
	}*/
