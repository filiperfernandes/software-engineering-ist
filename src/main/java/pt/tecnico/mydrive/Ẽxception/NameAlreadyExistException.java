package pt.tecnico.mydrive.Ẽxception;

public class NameAlreadyExistException extends mydriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingName;
    
    public NameAlreadyExistException(String conflictingName) {
    }

    public String getconflictingName(String conflictingName) {
       
        return conflictingName;

    }

    @Override
    public String getMessage() {
        return "This name " + conflictingName + " is already being used";
    }
}	
	

