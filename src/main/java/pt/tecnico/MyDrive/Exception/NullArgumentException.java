package pt.tecnico.MyDrive.Exception;

public class NullArgumentException extends MyDriveException  {

	private static final long serialVersionUID = 1L;


	public NullArgumentException() {
	}

	@Override
	public String getMessage() {
		return "Null argument";
	}

}
