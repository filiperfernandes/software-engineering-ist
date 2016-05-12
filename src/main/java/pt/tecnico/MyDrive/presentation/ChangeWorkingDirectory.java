package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.ChangeDirectoryService;
import pt.tecnico.MyDrive.presentation.MdShell;

public class ChangeWorkingDirectory extends MdCommand{
	
	private MdShell s;
	
	public ChangeWorkingDirectory(Shell sh) {
		super(sh, "cwd", "changes working directory");
		s= ((MdShell) sh);
	}
	
	
	
	@Override
	void execute(String[] args) {
		String path;
		if (args.length < 1){
		    throw new RuntimeException("USAGE: "+name()+" <path>");
		}
		else{
			ChangeDirectoryService c = new ChangeDirectoryService(args[0], s.getSessionToken());
			c.execute();
			path = c.result();
		}
		System.out.println(path);
	}
}
