package pt.tecnico.MyDrive.Ẽxception;

public class DirectoryIsNotEmptyException extends mydriveException {

    private static final long serialVersionUID = 1L;

    public DirectoryIsNotEmptyException() {
        super("That durectory is not empty"); 
    }

}
