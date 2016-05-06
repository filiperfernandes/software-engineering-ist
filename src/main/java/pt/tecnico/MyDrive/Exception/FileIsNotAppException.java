package pt.tecnico.MyDrive.Exception;

public class FileIsNotAppException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	public FileIsNotAppException() {
		super("The File with that name isn't an App");
	}
}
