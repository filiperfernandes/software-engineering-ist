package pt.tecnico.MyDrive.Exception;

public class PathToBigException extends MyDriveException  {

    private static final long serialVersionUID = 1L;


    public PathToBigException() {
    }

    @Override
    public String getMessage() {
        return "This Path has more than 1024 characters";
    }

}
