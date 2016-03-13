package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.Exception.*;

import pt.tecnico.MyDrive.domain.MyDrive;

public class File extends File_Base {

	protected File() {}

	public File(Integer id, String name,  String permissions) {
		super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		setPermissions(permissions);
	}



}
