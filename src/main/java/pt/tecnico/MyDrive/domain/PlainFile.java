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
	public void removePlainFile(){
		(this.getUser()).removeFile(this);
		(this.getDirectory()).removeFile(this);
		this.deleteDomainObject();
	}
	
/*	public void readPlainFile(String path) {
		MyDrive md = MyDrive.getInstance();
		String dirname = "";
		Directory dir = md.getRootdir() ;
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c!=null){
				if(c.equals(0)){
					c++;
				}
				else if(ch == '/'){
					dir = (Directory) (dir.getFileByName(dirname));
					dirname="";
				}
				else{
					dirname += ch;
				}
			}
			else{
				break;
			}
			System.out.println(((PlainFile) (dir.getFileByName(dirname))).getData());
		}
=======
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
>>>>>>> 45c6eb58f7c34f375f4f3688736ed7be23596c6d
	}*/

	public Element xmlExport() throws ExportXmlException {
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
