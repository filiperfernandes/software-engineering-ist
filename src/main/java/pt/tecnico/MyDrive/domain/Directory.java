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
    /*
	public File getFileByName(String name){
		
		MyDrive md = MyDrive.getInstance();
		for (File file: md.getRootdir().getFileSet()){
			if(file.getName().equals(name)){
				return file;
			}
		}
		System.out.println("returning null");
		return null;
	}*/
}
