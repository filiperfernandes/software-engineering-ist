package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

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

	public File removeFile(Integer id, String name,  String permissions){
		MyDrive md = MyDrive.getInstance();
		Directory d = new Directory(md.getCnt(), name, permissions);
		(md.getCurrentdir()).removeFile(d);
		return d;

}}
