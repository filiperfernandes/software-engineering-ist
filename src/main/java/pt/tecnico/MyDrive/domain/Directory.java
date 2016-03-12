package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

public class Directory extends Directory_Base {
    
	public Directory( Integer id, String name, DateTime lastModif, String permissions) {
        super();
        setId(id);
        setName(name);
        setLastModif(lastModif);
        setPermissions(permissions);
        setDimension(2);
    }
    
}
