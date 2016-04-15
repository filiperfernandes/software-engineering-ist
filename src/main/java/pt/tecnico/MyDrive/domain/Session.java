package pt.tecnico.MyDrive.domain;

import java.math.BigInteger;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Session extends Session_Base {

	public Session(MyDrive md, String username, String password) {
		super();
		setToken(new BigInteger(64, new Random()).longValue());
		MyDrive muuu = MyDrive.getInstance();
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


}


