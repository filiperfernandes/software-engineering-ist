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
				System.out.println("0" + user.getUsername() + " \n");
				this.setCurrentdir(user.getHomedir());
				System.out.println("0" + (user.getHomedir()).getName() + " \n");
				md.addSession(this);
			}
	}
}


