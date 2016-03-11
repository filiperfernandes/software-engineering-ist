package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

import pt.tecnico.MyDrive.domain.MyDrive;

public class File extends File_Base {
    
	protected File() {}
   
    public File(Integer id, String owner, String name, String pathToFile, DateTime lastModif, String permissions) {
        super();

        setId(id);
        setOwner(owner);
        setName(name);
        setPathToFile(pathToFile);
        setLastModif(lastModif);
        setPermissions(permissions);
    }
    
}
