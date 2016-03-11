package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;


public class PlainFile extends PlainFile_Base {
	
    protected PlainFile() {}
    
    public PlainFile(Integer id,  String name, DateTime lastModif, String permissions, String data) {
        super();
        setId(id);
        setName(name);
        setLastModif(lastModif);
        setPermissions(permissions);
        setData(data);
    }
    
}
