package pt.tecnico.MyDrive.presentation;
import java.util.Map.Entry;
import java.util.TreeMap;

import pt.tecnico.MyDrive.domain.AmbientVariable;
import pt.tecnico.MyDrive.domain.MyDrive;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.service.AddAmbientVariableService;
import pt.tecnico.MyDrive.service.LoginUserService;
import pt.tecnico.MyDrive.presentation.MdShell;

public class Environment extends MdCommand{
	
	private MdShell s;
	
	public Environment(Shell sh) { 
		super(sh, "env", "create or exchange an Environment Variable");
		s = ((MdShell) sh);
	}

	@Override
	void execute(String[] args) {
		
		if (args.length > 2) {
			throw new RuntimeException("USAGE: "+name()+" <name> [value]");
		}
		
		if (args.length == 0) {
			
			AddAmbientVariableService lst = new AddAmbientVariableService(s.getSessionToken(), null, null);
			lst.execute();
			TreeMap<String,String> map = lst.result();			
			
			for(Entry<String, String> entry: map.entrySet()){
				System.out.println(entry.getKey() + "=" + entry.getValue());
				
			}
		}
		
		if (args.length == 1) {
			AddAmbientVariableService lst = new AddAmbientVariableService(s.getSessionToken(),args[0], null);
			lst.execute();
			TreeMap<String,String> map = lst.result();
			
			System.out.println(map.get(args[0]));
			
		}
		
		if (args.length == 2) {
			new AddAmbientVariableService(s.getSessionToken(), args[0], args[1]).execute();
		}
		
		
	}
		
}
