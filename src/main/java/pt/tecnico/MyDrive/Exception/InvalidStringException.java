package pt.tecnico.MyDrive.Exception;

public class InvalidStringException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String InvalidString;

    public InvalidStringException(String InvString) {
        InvalidString = InvString;
    }

    public String getInvalidString() {
        return InvalidString;

    }

    @Override
    public String getMessage() {
        return "This Input " + getInvalidString() + " is invalid";
    }
}
