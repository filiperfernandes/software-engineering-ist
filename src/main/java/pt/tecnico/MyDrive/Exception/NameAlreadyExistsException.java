package pt.tecnico.MyDrive.Exception;

public class NameAlreadyExistsException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String conflictingName;

    public NameAlreadyExistsException(String confName) {
        conflictingName = confName;
    }

    public String getConflictingName() {
        return conflictingName;

    }

    @Override
    public String getMessage() {
        return "This name " + getConflictingName() + " is already being used";
    }
}
