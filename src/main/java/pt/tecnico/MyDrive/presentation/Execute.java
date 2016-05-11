package pt.tecnico.MyDrive.presentation;
import pt.tecnico.MyDrive.service.ExecuteFileService;


public class Execute extends MdCommand{

	public Execute(Shell sh){super(sh,"path","args to execute");}
	public void execute(String [] args){
		if(args.length < 1)
			throw new RuntimeException("USAGE: "+ name()+"");
		new ExecuteFileService(Integer.parseInt(args[0]),args[1],args[2]).execute();
	}

}