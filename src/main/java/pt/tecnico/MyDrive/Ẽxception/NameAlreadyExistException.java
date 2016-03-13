package pt.tecnico.MyDrive.Ẽxception;

public class NameAlreadyExistException extends mydriveException {

    private static final long serialVersionUID = 1L;

    private String conflicName;
    
    public NameAlreadyExistException(String conflictingName) {
    	conflicName = conflictingName ;
    }

    public String getconflictingName() {
       
        return conflicName;

    }

    @Override
    public String getMessage() {
        return "This name " + getconflictingName() + " is already being used";
    }
}	
	

