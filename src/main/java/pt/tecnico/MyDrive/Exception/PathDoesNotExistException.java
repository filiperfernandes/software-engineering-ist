package pt.tecnico.MyDrive.Exception;

public class PathDoesNotExistException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String PathName;

    public PathDoesNotExistException(String Pname) {
        PathName = Pname;
    }

    public String getPathName() {
        return PathName;

    }

    @Override
    public String getMessage() {
        return "This Path " + getPathName() + " does not exist";
    }
}
