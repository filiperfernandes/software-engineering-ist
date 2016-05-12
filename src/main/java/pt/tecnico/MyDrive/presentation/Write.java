package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.WriteFileService;

public class Write extends MdCommand{

	public Write(Shell sh) { super(sh, "update", "'write <text>' to change text of path"); }

	@Override
	void execute(String[] args) {
		if (args.length < 1){
			throw new RuntimeException("USAGE: "+name()+" <update path text>");
		}

		if (args.length >1){
			new WriteFileService(args[0], Long.valueOf(args[1]), args[2]).execute();
		}

	}

}
