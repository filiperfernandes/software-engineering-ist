package pt.tecnico.MyDrive.Exception;

public class PermissionDeniedException extends MyDriveException {
	
	private static final long serialVersionUID = 1L;


	public PermissionDeniedException() {
	}

	@Override
	public String getMessage() {
		return "Permission denied.";
	}

}
