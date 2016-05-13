package pt.tecnico.MyDrive.presentation;
import pt.tecnico.MyDrive.service.ExecuteFileService;


public class Execute extends MdCommand{

	private MdShell s;
	public Execute(Shell sh){
		super(sh,"do","args to execute");
		s=((MdShell) sh);

	}
	public void execute(String [] args){
		if(args.length < 1){
			throw new RuntimeException("USAGE: "+ name()+" <path> [args]");
		}
		else{
			ExecuteFileService e = new ExecuteFileService (s.getSessionToken(),args[0],args[1]);
			e.execute();

		}
	}

}