package pt.tecnico.MyDrive.domain;

import java.math.BigInteger;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Period;

import pt.tecnico.MyDrive.Exception.SessionDoesNotExistException;

public class Session extends Session_Base {

	public Session(MyDrive md, String username, String password) {
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
		if(user.checkPassword(password)){
			//criar timer de 2 horas
			updateSessions(muuu);
			user.addSession(this);
			setCurrentdir(user.getHomedir());
			DateTime time = new DateTime();
			setState(time);
			muuu.addSession(this);
		}


	}

	public void updateSessions(MyDrive md){
		Period period ;
		DateTime now = new DateTime();
		for(Session s : md.getSessionSet()){
			period= new Period(s.getState(), now);
			if(period.getHours()>=2){md.removeSession(s);}
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


