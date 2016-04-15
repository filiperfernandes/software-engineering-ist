package pt.tecnico.MyDrive.Exception;

public class DirectoryDoesNotExistException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	private String DirectoryName;

	public DirectoryDoesNotExistException(String Dname) {
		DirectoryName = Dname;
	}

	public String getDirectoryName() {
		return DirectoryName;

	}

	@Override
	public String getMessage() {
		return "This Directory " + getDirectoryName() + " does not exist \n";
	}
}
