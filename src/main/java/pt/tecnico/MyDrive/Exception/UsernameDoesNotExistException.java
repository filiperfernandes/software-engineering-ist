package pt.tecnico.MyDrive.Exception;

public class UsernameDoesNotExistException extends MyDriveException {

    private static final long serialVersionUID = 1L;

    private String Username;

    public UsernameDoesNotExistException(String Uname) {
        Username = Uname;
    }

    public String getUsername() {
        return Username;

    }

    @Override
    public String getMessage() {
        return "The user " + getUsername() + " does not exist \n";
    }
}
