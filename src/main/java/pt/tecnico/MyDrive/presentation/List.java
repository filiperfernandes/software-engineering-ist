package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.ListDirectoryService;

public class List extends MdCommand{

	public List(Shell sh) { super(sh, "list", "'list <name>' to list a entries directory"); }

	@Override
	void execute(String[] args) {
		if (args.length < 1){
			throw new RuntimeException("USAGE: "+name()+" <username> [password]");
		}

		if (args.length >1){
			new ListDirectoryService(Integer.parseInt(args[0])).execute();
		}
	}

}

