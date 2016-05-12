package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.domain.PlainFile;
import pt.tecnico.MyDrive.service.WriteFileService;

public class Write extends MdCommand{

	private MdShell s;

	public Write(Shell sh) {
		super(sh, "update", "'write <text>' to change text of path"); 
		s= ((MdShell) sh);
	}
	
	@Override
	void execute(String[] args) {
		PlainFile text=null;
		if (args.length < 1){
			throw new RuntimeException("USAGE: "+name()+" <update path text>");
		}

		if (args.length >1){
			WriteFileService w = new WriteFileService(args[0], s.getSessionToken(), args[2]);
			w.execute();	
			text = w.result();
		}
		System.out.println(text);
	}

}
