package pt.tecnico.MyDrive.domain;


import org.jdom2.Element;
import org.jdom2.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;

public class MyDrive extends MyDrive_Base {
	/*
	public MyDrive(Integer cnt) {
		super();
		setCounter(cnt);


	}
	 */
	public static MyDrive getInstance() {
		MyDrive md = FenixFramework.getDomainRoot().getMydrive();
		if (md != null){
			return md;}

		return new MyDrive();
	}

	private MyDrive() {
		setRoot(FenixFramework.getDomainRoot());
		setCounter(0);
		SuperUser su = new SuperUser();
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
		System.out.println("fodeu "+username);
		return null;
	}

	public void xmlImport(Element element) {

		MyDrive md = MyDrive.getInstance();

		for (Element node: element.getChildren("user")) {
			String username = node.getAttribute("username").getValue();
			User user = getUserByUsername(username);// falta criar funcao getPersonByName(name)

			if (user == null){ // Does not exist
				user = new User(this, username);
				System.out.println("adicionou "+user);
			}

			user.xmlImport(node);// deve ser isto os de baixo nao interessa
			//user.setUsername(xmlImport(node));
			//person.xmlImport(node); falta verificar aqui
		}
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