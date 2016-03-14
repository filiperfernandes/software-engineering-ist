package pt.tecnico.MyDrive.domain;

import java.io.IOException;

import org.jdom2.Element;
import org.joda.time.DateTime;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.Exception.*;

import pt.tecnico.MyDrive.MyDriveApplication;

public class Directory extends Directory_Base {

	public Directory( Integer id, String name,  String permissions) {
		super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		setPermissions(permissions);
		setDimension(2);

	}

	public File getFileByName(String name){
		for (File file: this.getFileSet()){
			if(file.getName().equals(name)){
				return file;
			}
		}
		throw new FileDoesNotExistException(name);
	}

	public void removeDir(){
		setDirectory(null);
		deleteDomainObject();
	}


	//XML export constructor
	public Directory(MyDrive md, String name) {
		super();
		setName(name);
		//setMydrive(md);
	}

	//XML import constructor
	public Directory(MyDrive md, Element xml) {
		super();
		xmlImport(xml);
		//setMydrive(md);
	}


	/*public Directory createDirectory(String name,  String permissions){
		MyDrive md = MyDrive.getInstance();
		Directory d = new Directory(md.getCnt(), name, permissions);
		(md.getCurrentdir()).addFile(d);
		return d;
	}*/



	//Not working...
	/*public Directory getHome(){
		MyDrive md = MyDrive.getInstance();
		md.getRootdir();
		Directory home = (Directory) Directory.getFileByName("home");
		System.out.println("Dir O HOME E:" + home);
		return home;
	}*/

	//Return File by ID
	public File getFileByID(String id){

		MyDrive md = MyDrive.getInstance();
		for (File file: this.getFileSet()){
			String x = String.valueOf(file.getId());
			if(x.equals(id)){
				return file;
			}
		}
		throw new FileDoesNotExistException(id);
	}



	//Import XML Directory
	public void xmlImport(Element personElement) /*throws ImportDocumentException */{
		DateTime date = new DateTime();

		MyDrive md = MyDrive.getInstance();

		// clear current phone book | current mydrive
		/*for (File u: ((Directory) md).getFileSet()){
			System.out.println("vai remover " + u);
			md.removeUser(u);
			System.out.println("removeu user "+ u);
		}ainda falta por isto a funcionar*/



		
		String sid = personElement.getAttribute("id").getValue();

		Integer id = Integer.parseInt(sid);

		String name = personElement.getChildText("name");

		String owner = personElement.getChildText("owner");

		String perm = personElement.getChildText("perm");

		System.out.println("sid: "+sid + " id: "+id + " name: "+name + " owner: "+owner+ " perm: "+perm);
		//Directory toAdd = new Directory (id, name, date, perm);


		System.out.println("-----vamos procurar a pasta home no directorio ");

		//Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		//System.out.println("home = "+home + " rootdir= "+md.getRootdir());
		//System.out.println();



		//Directory userDir = (Directory) getHome().getFileByName(owner);
		//getHome();

		//MyDriveApplication.acabaAi(owner, id, name, date, perm);
		System.out.println("aqui e nao e pouco");
		//home.addFile(userDir);

		//userDir.addFile(toAdd);

	}

	//Export XML Directory
	public Element xmlExport() {
		Element dirElement = new Element("dir");
		dirElement.setAttribute("id", String.valueOf(getId()));

		//Path
		Element dirPath = new Element("path");
		dirPath.setText(getPath());
		dirElement.addContent(dirPath);

		//Name
		Element dirName = new Element("name");
		dirName.setText(getName());
		dirElement.addContent(dirName);

		//Owner
		Element dirOwner = new Element("owner");
		dirOwner.setText(getUser().getName());
		dirElement.addContent(dirOwner);

		//Perm
		Element dirPerm = new Element("perm");
		dirPerm.setText(getPermissions());
		dirElement.addContent(dirPerm);


		return dirElement;
	}

}
