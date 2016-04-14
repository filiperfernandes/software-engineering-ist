package pt.tecnico.MyDrive.Exception;

public class UserDoesNotHavePermissionsException  extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public UserDoesNotHavePermissionsException() {
		
        super("User doesn't have permission\n");

	}
    
}