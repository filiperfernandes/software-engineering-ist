package pt.tecnico.MyDrive.Exception;

public class UsernameAlreadyExistsException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	private String conflictingName;

	public UsernameAlreadyExistsException(String confName) {
		conflictingName = confName;
	}

	public String getConflictingName() {
		return conflictingName;

	}

	@Override
	public String getMessage() {
		return "This Username " + getConflictingName() + " is already being used";
	}
}
