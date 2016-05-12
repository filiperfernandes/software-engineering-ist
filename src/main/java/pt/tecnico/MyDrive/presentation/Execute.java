package pt.tecnico.MyDrive.presentation;
import pt.tecnico.MyDrive.service.ExecuteFileService;


public class Execute extends MdCommand{

	private MdShell s;
	public Execute(Shell sh){
		super(sh,"path","args to execute");
		s=((MdShell) sh);
	}
	public void execute(String [] args){
		if(args.length < 1){
			throw new RuntimeException("USAGE: "+ name()+"");
		}
		else{
			ExecuteFileService e = new ExecuteFileService (s.getSessionToken(),args[0],args[1]);
			e.execute();

		}
		System.out.println("got it");
		}

}