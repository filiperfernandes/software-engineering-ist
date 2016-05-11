package pt.tecnico.MyDrive.presentation;

import pt.tecnico.MyDrive.service.ListDirectoryService;
import pt.tecnico.MyDrive.service.dto.FileInfoDto;

public class List extends MdCommand{
	
	private MdShell s;

	public List(Shell sh) { 
		super(sh, "ls", "'list <name>' to list a entries directory"); 
		s= ((MdShell) sh);
	}

	@Override
	void execute(String[] args) {
		if (args.length < 1){
			ListDirectoryService l = new ListDirectoryService(s.getSessionToken(), "");
			l.execute();
			for(FileInfoDto f : l.result()){
				System.out.println(f.getPermissions() + " " + f.getOwner() + " " + f.getId() + " " + f.getName());
			}
		}
		else{
			ListDirectoryService l = new ListDirectoryService(s.getSessionToken(), args[0]);
			l.execute();
			for(FileInfoDto f : l.result()){
				System.out.println(f.getPermissions() + " " + f.getOwner() + " " + f.getId() + " " + f.getName());
			}
		}
	}

}

