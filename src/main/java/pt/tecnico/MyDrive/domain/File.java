package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

import pt.tecnico.MyDrive.domain.MyDrive;

public class File extends File_Base {

	protected File() {}

	public File(Integer id, String name, DateTime lastModif, String permissions) {
		super();

		setId(id);
		setName(name);
		setLastModif(lastModif);
		setPermissions(permissions);
	}

	public File getFileByName(String name){

		MyDrive md = MyDrive.getInstance();
		for (File file: md.getRootdir().getFileSet()){
			if(file.getName().equals(name)){
				return file;
			}
		}
		System.out.println("returning null");
		return null;
	}

}
