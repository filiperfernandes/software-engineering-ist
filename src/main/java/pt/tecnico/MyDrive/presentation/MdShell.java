package pt.tecnico.MyDrive.presentation;

public class MdShell extends Shell {
	
	private long token;

	public static void main(String[] args) throws Exception {
		MdShell sh = new MdShell();
		sh.execute();
	}

	public MdShell() { // add commands here
		super("MyDrive");
		new Login(this);
		new ChangeWorkingDirectory(this);
	    new List(this);
		//	    new Execute(this);
		//	    new Write(this);
		//	    new Environment(this);
		//	    new Key(this);
	}
	
	public long getSessionToken(){
		return token;
	}
	
	public void setSessionToken(long t){
		token = t;
	}
}
