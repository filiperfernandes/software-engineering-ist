package pt.tecnico.MyDrive.Exception;

public class FileIsDirectoryException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public FileIsDirectoryException() {
        super("The File with that name is a Directory");
    }
}
