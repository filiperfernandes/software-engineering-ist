package pt.tecnico.MyDrive.domain;

import java.lang.String;

public class User extends User_Base {
    private String username;
    
    private String name;

    private String password;

    private String mask;


   //HomeDir should probably be an object of the Dir type and not a String 
    protected User (){}

    public User(String username) {
      setPassword(username);
      setName(username);
      setMask("rwxd----");
      //setFilename("username");
    }
    public String ToString(){
      return (username + " - " + name + " - " + mask);
    }


    public String changeUsername(String newUsername){
      
      if (checkValidString(newUsername)==false){
        return "Not a valid String";
        //exception?
      }
      else if (newUsername.equals("root")){
        return "error that name is reserved";
        //throw exceptions?
      }
      else if (newUsername.equals(getName())){
        return "your name was already that one";
        //throw exceptions?
      }
      else{
        setUsername(newUsername);
       // setFilename(newUsername);
        return  "sucess";
      }

      // falta verificar se o username pedido ja se encontra em uso+++
    }


    public String changeName(String newName){
      if (checkValidString(newName)==false){
        return "Not a valid String";
        //exception?
      }
      else{
        setName(newName);
        return "sucess";
      }
    }

    public boolean checkValidString(String check){
      for(int i=0; i<check.length();i++){
          int asciiCheck = (int) check.charAt(i);
        if (asciiCheck > 47 && asciiCheck < 58 || asciiCheck > 64 && asciiCheck <  91 || asciiCheck > 96 && asciiCheck < 123) {
          continue;
        }
        return false;
        //throw a exception  
      }
      return true;
    }

}
