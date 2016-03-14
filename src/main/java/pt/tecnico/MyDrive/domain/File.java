package pt.tecnico.MyDrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;

public class File extends File_Base {

	protected File() {}

	public File(Integer id, String name,  String permissions) {
		super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		setPermissions(permissions);
	}

	public String getPath(){
		String path = "";
		File f = this;
		while(!((f.getName()).equals("/"))){
			path = "/" + f.getName() + path;
			System.out.println(f.getName());
			f = f.getDirectory();
		}
		return path;
	}
}
