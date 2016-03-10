package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

public class Directory extends Directory_Base {
    
    public Directory(Integer id, String owner, String name, String pathToFile, DateTime lastModif, String permissions, Integer dimension, String filename) {
        super();
        setId(id);
        setOwner(owner);
        setName(name);
        setPathToFile(pathToFile);
        setLastModif(lastModif);
        setPermissions(permissions);
        setDimension(dimension);
        setFilename(filename);
    }
    
}
