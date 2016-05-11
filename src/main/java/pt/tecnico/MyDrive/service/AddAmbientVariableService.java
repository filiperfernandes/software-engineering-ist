package pt.tecnico.MyDrive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.domain.AmbientVariable;
import pt.tecnico.MyDrive.domain.Session;

public class AddAmbientVariableService extends MyDriveService{

	private String name;
	private String value;
	private long token;
	private TreeMap<String,String> map;

	public AddAmbientVariableService(long token, String name, String value) {
		this.name=name;
		this.value=value;
		this.token=token;
	}

	@Override
	protected void dispatch() throws MyDriveException {

		if(name==null){
			throw new NullArgumentException();
		}
		else if(value==null){
			throw new NullArgumentException();
		}

		map = new TreeMap<String,String>();
		Session s = Session.getSessionByToken(token);
		Boolean b = true;

		for(AmbientVariable a : s.getAmbientvariableSet()){
			if(a.getName().equals(name)){
				b=false;
				a.setValue(value);
			}
		}	
		
		if(b){
			AmbientVariable amb = new AmbientVariable(name, value);
			s.addAmbientvariable(amb);
		}
		
		for(AmbientVariable a :s.getAmbientvariableSet()){
			map.put(a.getName(), a.getValue());
		}
	}

	public final TreeMap<String,String> result(){
		return map;
	}
	

}