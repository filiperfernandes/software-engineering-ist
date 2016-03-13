package pt.tecnico.MyDrive.Ẽxception;

public class mydriveException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public mydriveException() {
    }

    public mydriveException(String msg) {
        super(msg);
    }
}