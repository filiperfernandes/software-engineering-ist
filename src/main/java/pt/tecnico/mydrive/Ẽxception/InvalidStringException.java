package pt.tecnico.mydrive.Ẽxception;

public class InvalidStringException extends mydriveException {

    private static final long serialVersionUID = 1L;

    private String InvalidString;

    public String InvalidStringException(String InvalidString) {
       
        return InvalidString;

    }

    @Override
    public String getMessage() {
        return "This input " + InvalidString + " is invalid";
    }
}	
	