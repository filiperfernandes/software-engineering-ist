package pt.tecnico.MyDrive.Exception;

public class DirectoryIsNotEmptyException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public DirectoryIsNotEmptyException() {
        super("Directory is not empty \n");
    }
}
