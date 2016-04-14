package pt.tecnico.MyDrive.domain;

import java.math.BigInteger;
import java.util.Random;

public class Session extends Session_Base {

	public Session(MyDrive md, String username, String password) {
		super();
		setToken(new BigInteger(64, new Random()).longValue());
			User user = md.getUserByUsername(username);
			if(user.checkPassword(password)){
				//criar timer de 2 horas
				user.addSession(this);
				setCurrentdir(user.getHomedir());
				md.addSession(this);
			}
	}
}


