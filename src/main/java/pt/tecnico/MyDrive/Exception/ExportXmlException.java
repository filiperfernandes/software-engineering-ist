package pt.tecnico.MyDrive.Exception;

public class ExportXmlException extends MyDriveException {

	private static final long serialVersionUID = 1L;

	public ExportXmlException() {
		super("Error in exporting information to XML");
	}
}
