package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;

public class Directory extends Directory_Base {
    
    public Directory(Integer id, String owner, String name, String pathToFile, DateTime lastModif, String permissions, Integer dimension, String filename) {
        super();
        setDimension(dimension);
        setFilename(filename);
    }
    
}
