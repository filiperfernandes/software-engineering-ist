package pt.tecnico.MyDrive.domain;

import java.math.BigInteger;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Session extends Session_Base {

	public Session(MyDrive md, String username, String password) {
		super();
		setToken(new BigInteger(64, new Random()).longValue());

		User user = md.getUserByUsername(username);
		if(user.checkPassword(password)){
			//criar timer de 2 horas
			//updateSessions();
			user.addSession(this);
			setCurrentdir(user.getHomedir());
			DateTime time = new DateTime();
			setState(time);
			md.addSession(this);
		}


	}

	public void updateSessions(){
		Period period ;
		DateTime now = new DateTime();
		MyDrive md = getMydrive();
		for(Session s : md.getSessionSet()){
			period= new Period(s.getState(), now);
			if(period.getHours()>=2){md.removeSession(s);}
		}
	}

}


