package pt.tecnico.MyDrive.domain;

import java.io.IOException;

import org.joda.time.DateTime;

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
		System.out.println("returning null");
		return null;
	}

}

/*public Directory createDirectory(String name,  String permissions){
		MyDrive md = MyDrive.getInstance();
		Directory d = new Directory(md.getCnt(), name, permissions);
		(md.getCurrentdir()).addFile(d);
		return d;
	}*/



