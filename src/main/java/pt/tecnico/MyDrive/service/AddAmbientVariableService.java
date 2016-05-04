package pt.tecnico.MyDrive.service;

import java.util.List;

import pt.tecnico.MyDrive.Exception.MyDriveException;
import pt.tecnico.MyDrive.Exception.NullArgumentException;
import pt.tecnico.MyDrive.domain.AmbientVariable;
import pt.tecnico.MyDrive.domain.Session;

public class AddAmbientVariableService extends MyDriveService{

	private String name;
	private String value;
	private long token;
	private List<AmbientVariable> list;

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

		Session s = Session.getSessionByToken(token);

		for(AmbientVariable a : s.getAmbientvariableSet()){
			if(a.getName().equals(name)){
				a.setValue(value);
			}
		}	
		
		AmbientVariable amb = new AmbientVariable(name, value);
		s.addAmbientvariable(amb);

		for(AmbientVariable a :s.getAmbientvariableSet()){
				list.add(a);
		}
	}

	public final List<AmbientVariable> result(){
		return list;
	}


}