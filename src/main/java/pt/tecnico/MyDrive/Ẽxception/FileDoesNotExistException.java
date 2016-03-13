package pt.tecnico.MyDrive.Ẽxception;

public class FileDoesNotExistException extends mydriveException {

    private static final long serialVersionUID = 1L;

    private String FileName;
    
    public FileDoesNotExistException(String FName) {
    	FileName = FName ;
    }

    public String getFileName() {
       
        return FileName;

    }

    @Override
    public String getMessage() {
        return "This File " + getFileName() + " does not exist";
    }
}	
	


