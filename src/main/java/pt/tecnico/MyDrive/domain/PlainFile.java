package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;


public class PlainFile extends PlainFile_Base {
	
    protected PlainFile() {}
    
    public PlainFile(Integer id, String owner, String name, String pathToFile, DateTime lastModif, String permissions, String data) {
        super();
        setId(id);
        setOwner(owner);
        setName(name);
        setPathToFile(pathToFile);
        setLastModif(lastModif);
        setPermissions(permissions);
        setData(data);
    }
    
}
