package pt.tecnico.MyDrive.service.dto;


public class FileInfoDto implements Comparable<FileInfoDto>  {


	private String name;
	private String permissions;
	private String owner;
	private int id;

	public FileInfoDto(String name, String permissions, String owner, int id) {
		this.name = name; 
		this.permissions = permissions;
		this.owner = owner;
		this.id = id;
	}

	public final String getName() {
		return this.name;
	}

	public final String getPermissions() {
		return this.permissions;
	}

	public final String getOwner() {
		return this.owner;
	}

	public final int getId(){
		return this.id;
	}

	@Override
	public int compareTo(FileInfoDto other) {
		return getName().compareTo(other.getName());
	}

}
