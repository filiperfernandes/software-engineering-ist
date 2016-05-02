package pt.tecnico.MyDrive.Exception;

public class InvalidPasswordException extends MyDriveException {


	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super("Invalid Password");
	}
	
	public InvalidPasswordException(String msg){
		super(msg);
	}
}
