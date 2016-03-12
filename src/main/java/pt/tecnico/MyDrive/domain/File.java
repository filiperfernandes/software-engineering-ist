package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

public class File extends File_Base {

	protected File() {}

	public File(Integer id, String name, DateTime lastModif, String permissions) {
		super();

		setId(id);
		setName(name);
		setLastModif(lastModif);
		setPermissions(permissions);
	}

	

}
