package pt.tecnico.MyDrive.Exception;

public class InvalidTypeException extends MyDriveException {

    private static final long serialVersionUID = 1L;


    public InvalidTypeException() {

    }


    @Override
    public String getMessage() {
        return "The file type is invalid \n";
    }
}
