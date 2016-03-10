package pt.tecnico.MyDrive.domain;

public class User extends User_Base {
    private String username
    
    private String name;

    private String password;

    private String mask;

   //HomeDir should probably be an object of the Dir type and not a String 

    public User(String username) {
      setPassword(username);
      setName(username);
      setMask("rwxd----");
    }


}
