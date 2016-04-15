package pt.tecnico.MyDrive.Exception;

public class SessionDoesNotExistException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	private long SessionToken;

	public SessionDoesNotExistException(long token) {
		SessionToken = token;
	}

	public long getSessionToken() {
		return SessionToken;

	}

	@Override
	public String getMessage() {
		return "This Session " + getSessionToken() + " does not exist \n";
	}
}