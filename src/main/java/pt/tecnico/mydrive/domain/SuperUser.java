package pt.tecnico.MyDrive.domain;

public class SuperUser extends SuperUser_Base {
    
    public SuperUser() {
        super();
        setUsername("root");
        setPassword("***");
        setMask("rwxdr-x-");
    }

    public String removeUser(String user){
    	if (user.equals("root")){
    		return "that user can't be removed";
    		//Maybe throw and exception here
    	}
    	else{
    		//remove the user object from the list
    		//maybe remove his Directory?
    		return "done";
    	}
    }


    
}
