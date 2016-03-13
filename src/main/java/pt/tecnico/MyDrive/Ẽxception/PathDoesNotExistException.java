package pt.tecnico.MyDrive.Ẽxception;

public class PathDoesNotExistException extends mydriveException {

    private static final long serialVersionUID = 1L;

    private String PathName;
    
    public PathDoesNotExistException(String PName) {
    	PathName = PName ;
    }

    public String getPathName() {
       
        return PathName;

    }

    @Override
    public String getMessage() {
        return "This Path " + getPathName() + " does not exist";
    }
}	
	