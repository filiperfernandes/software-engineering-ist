package pt.tecnico.MyDrive.Exception;

public class SessaoDoesNotExistException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	private long SessaoToken;

	public SessaoDoesNotExistException(long token) {
		SessaoToken = token;
	}

	public long getSessaoToken() {
		return SessaoToken;

	}

	@Override
	public String getMessage() {
		return "This Sessao " + getSessaoToken() + " does not exist \n";
	}
}