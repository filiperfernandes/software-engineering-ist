package pt.tecnico.MyDrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.Exception.*;


public class PlainFile extends PlainFile_Base {

	protected PlainFile() {}

	public PlainFile(Integer id,  String name, String permissions, String data) {
		super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		setPermissions(permissions);
		setData(data);
	}

	public PlainFile(Integer id,  String name, DateTime lastModif, String permissions, String data) {
		super();
		setId(id);
		setName(name);
		setLastModif(lastModif);
		setPermissions(permissions);
		setData(data);
		
		
	}
	
	public PlainFile(MyDrive md, String name) {
		super();
		setName(name);
		setMydrive(md);
	}


	/*public PlainFile(MyDrive md, Element xml) { Uncomment to implement import
		super();
		xmlImport(xml);
		setMydrive(md);
	}*/

	public Element xmlExport() {
		Element plainElement = new Element("plain");
		plainElement.setAttribute("id", String.valueOf(getId()));

		//Path
		Element plainPath = new Element("path");
		plainPath.setText(getPath());
		plainElement.addContent(plainPath);

		//Name
		Element plainName = new Element("name");
		plainName.setText(getName());
		plainElement.addContent(plainName);

		//Owner
		Element plainOwner = new Element("owner");
		plainOwner.setText(getUser().getName());
		plainElement.addContent(plainOwner);

		//Permissions
		Element plainPerm = new Element("perm");
		plainPerm.setText(getPermissions());
		plainElement.addContent(plainPerm);
		
		//Contents
		Element plainContents = new Element("contents");
		plainContents.setText(getData());
		plainElement.addContent(plainContents);


		return plainElement;
	}

}
