package pt.tecnico.MyDrive.Exception;

public class FileIsPlainFileException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	public FileIsPlainFileException() {
		super("The File with that name is a Plain File");
	}
}
