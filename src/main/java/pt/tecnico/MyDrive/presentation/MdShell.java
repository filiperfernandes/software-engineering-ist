package pt.tecnico.MyDrive.presentation;

import java.util.HashMap;

public class MdShell extends Shell {
	
	private HashMap<String,Long> map = new HashMap<String, Long>();
	private long token;
	private String currentUser;
	
	public static void main(String[] args) throws Exception {
		MdShell sh = new MdShell();
		sh.execute();
	}

	public MdShell() { // add commands here
		super("MyDrive");
		new Login(this);
		new ChangeWorkingDirectory(this);
	    new List(this);
	    new Write(this);
	    new Execute(this);
		new Environment(this);
	    new Key(this);
	}
	
	public long getSessionToken(){
		return token;
	}
	
	public void setSessionToken(long t){
		token = t;
	}
	
	public String getSessionUser(){
		return currentUser;
	}
	
	public void setSessionUser(String s){
		currentUser = s;
	}
	
	public void addSessionToMap(String username, Long token){
		map.put(username, token);
	}
	
	public Long getTokenByUsername(String u){
		return map.get(u);
	}
}
