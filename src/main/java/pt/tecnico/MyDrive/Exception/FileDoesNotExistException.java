package pt.tecnico.MyDrive.Exception;

public class FileDoesNotExistException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	private String FileName;

	public FileDoesNotExistException(String Fname) {
		FileName = Fname;
	}

	public String getFileName() {
		return FileName;

	}

	@Override
	public String getMessage() {
		return "This File " + getFileName() + " does not exist";
	}
}
