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
	
	public Environment(Shell sh) { super(sh, "env", "create or exchange an Environment Variable"); }

	@Override
	void execute(String[] args) {
		
		
		if (args.length == 0) {
			
			//new AddAmbientVariableService(LoginUserService.result(),args[0], args[1]).execute();
			AddAmbientVariableService lst = new AddAmbientVariableService(s, null, null);
			lst.execute();
			TreeMap<String,String> map = lst.result();
			
			
			for(Entry<String, String> entry: map.entrySet()){
				System.out.println(entry.getKey() + "=" + entry.getValue() + "/n");
				
			}
		}
		if (args.length == 1) {
			AddAmbientVariableService lst = new AddAmbientVariableService(s,args[0], null);
			lst.execute();
			TreeMap<String,String> map = lst.result();
			
			System.out.println(map.get(args[1]));
			
		}
		if (args.length == 2) {
			new AddAmbientVariableService(s, args[0], args[1]).execute();
		}
		
		
	}
		

	
}
