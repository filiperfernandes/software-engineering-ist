package pt.tecnico.MyDrive.Ẽxception;

public class InvalidStringException extends mydriveException {

    private static final long serialVersionUID = 1L;

    private String InvalidString;

    public InvalidStringException(String InvString) {
       InvalidString = InvString ;
    }

    

    public String getInvalidString() {
       
        return InvalidString;

    }
    
    @Override
    public String getMessage() {
        return "This input " + getInvalidString() + " is invalid";
    }
}	
	