package pt.tecnico.MyDrive.domain;

import java.math.BigInteger;
import java.util.Random;

public class Sessao extends Sessao_Base {
    
	public Sessao() {
		super();
        this.setToken(new BigInteger(64, new Random()).longValue());
    }
    
    
}
