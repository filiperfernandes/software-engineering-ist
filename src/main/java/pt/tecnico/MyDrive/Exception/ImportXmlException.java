package pt.tecnico.MyDrive.Exception;

public class ImportXmlException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    public ImportXmlException() {
        super("Error in importing information from XML");
    }
}
