package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.service.WriteFileService;

public class Write extends MdCommand{

	private MdShell s;

	public Write(Shell sh) {
		super(sh, "update", "update contents of file"); 
		s= ((MdShell) sh);
	}
	
	@Override
	public void execute(String[] args) {
		PlainFile text=null;
		if (args.length < 2){
			throw new RuntimeException("USAGE: "+name()+" <path> <text>");
		}

		else{
			WriteFileService w = new WriteFileService(args[0], s.getSessionToken(), args[1]);
			w.execute();	
			text = w.result();
		}
		System.out.println(text);
	}

}
