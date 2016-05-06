package pt.tecnico.MyDrive.domain;

import java.math.BigInteger;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Period;

import pt.tecnico.MyDrive.Exception.InvalidPasswordException;
import pt.tecnico.MyDrive.Exception.PermissionDeniedException;
import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;

public class Session extends Session_Base {

	public Session(String username, String password) {
		super();
		MyDrive muuu = MyDrive.getInstance();
		boolean t = true;
		Long i = null ;
		while(t){
			i = new BigInteger(64, new Random()).longValue();
			boolean exists = false;
			for(Session s : muuu.getSessionSet()){
				if(s.getToken() == i){
					exists = true;
					break;
				}
			}
			if(!exists){
				t=false;
			}
		}
		setToken(i);
		User user = muuu.getUserByUsername(username);

		//All users except root and guest with valid password
		if(user.checkPassword(password)&user.getPassword().length()>=8){
			//criar timer de 2 horas
			updateSessions(muuu);
			user.addSession(this);
			setCurrentdir(user.getHomedir());
			DateTime time = new DateTime();
			setState(time);
			muuu.addSession(this);
			return;
		}
		//if user is Guest
		else if(user.getUsername().equals("Guest")&user.checkPassword(password)){
			updateSessions(muuu);
			user.addSession(this);
			setCurrentdir(user.getHomedir());
			setState(null);
			muuu.addSession(this);
			return;
		}
		//if user is root
		else if(user.getUsername().equals("root")&user.checkPassword(password)){
			updateSessions(muuu);
			user.addSession(this);
			setCurrentdir(user.getHomedir());
			DateTime time = new DateTime();
			setState(time);
			muuu.addSession(this);
			return;
		}

		else{
			throw new InvalidPasswordException("Password must be at least 8 characters long");
		}
	}

	@Override
	public void setUser(User u) { 
		throw new PermissionDeniedException(); 
	}

	@Override
	public void setMydrive(MyDrive m) { 
		throw new PermissionDeniedException(); 
	}

	public void updateSessions(MyDrive md){
		Period period ;
		DateTime now = new DateTime();
		for(Session s : md.getSessionSet()){
			if(s.getState().equals(null)){
				break;
			}
			else if(s.getUser().getUsername().equals("root")){
				period= new Period(s.getState(), now);
				if(period.getMinutes()>=10){md.removeSession(s);}
			}
			else{
				period= new Period(s.getState(), now);
				if(period.getHours()>=2){md.removeSession(s);}
			}
		}
	}

	public void remove() {
		this.setUser(null);
		this.setCurrentdir(null);
		this.setMydrive(null);
		deleteDomainObject();
	}


	public static Session getSessionByToken(long token){
		MyDrive md = MyDrive.getInstance();

		for(Session s : md.getSessionSet()){
			if(s.getToken()==token){
				return s;
			}
		}
		throw new SessionDoesNotExistException(token);
	}

}


