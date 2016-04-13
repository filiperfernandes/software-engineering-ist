package pt.tecnico.MyDrive.domain;

import pt.tecnico.MyDrive.Exception.SessaoDoesNotExistException;

public class Login extends Login_Base {
    
    public Login() {
        super();
    }
    public Sessao getSessaoByToken(long token){
    	for(Sessao s : this.getSessaoSet()){
    		if(s.getToken().equals(token)){
    			return s;
    		}
    	}
    	throw new SessaoDoesNotExistException(token);
    }
}
