package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;
import pt.tecnico.MyDrive.domain.Session;
import pt.tecnico.MyDrive.domain.Directory;
import pt.tecnico.MyDrive.Exception.PathDoesNotExistException;

public class Link extends Link_Base {
	
    
    public Link(Integer id,  String name, String path, long token) {
        super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		permissions(path, token);
		setData(path);
    }
    
    public void permissions(String path, long token){
    	
		MyDrive md = MyDrive.getInstance();
		Session session = Session.getSessionByToken(token);
		Directory dir = session.getCurrentdir();
		Directory rd = md.getRootdir();
		
		String name = "";
		String p = "";
		Integer c = 0;
		
		for(char ch : path.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			
			else if (ch=='/'){
				p += "/" + name ;
				name = "";
			}
			else{
				name += ch;
			}
		}
    	
    	if(checkPath(p, dir).equals("absolute")){
			Directory directory = Directory.getDirByPath(p, rd);
			File f = directory.getFileByName(name);
			this.setPermissions(f.getPermissions());
			
		}
		else if(checkPath(p, dir).equals("relative")){
			Directory directory = Directory.getDirByPath(p, dir);
			File f = directory.getFileByName(name);
			this.setPermissions(f.getPermissions());
		}
		else{
			throw new PathDoesNotExistException(p);
		}
    }
    
    private String checkPath(String path, Directory dir){

		MyDrive md = MyDrive.getInstance();
		Directory rd = md.getRootdir();
		rd.getPermissions();
		String dirname = "";
		Integer c = 0;
		String auxname = path + "/";
		for(char ch : auxname.toCharArray()){
			if(c.equals(0)){
				c++;
			}
			else if(ch == '/'){
				for (pt.tecnico.MyDrive.domain.File d : rd.getFileSet()){
					if(d.getName().equals(dirname)){
						if(d instanceof Directory ){
							return "absolute";
						}
					}
				}
				for (pt.tecnico.MyDrive.domain.File d2 : dir.getFileSet()){
					if(d2.getName().equals(dirname)){
						if(d2 instanceof Directory ){
							return "relative";
						}
					}
				}
				break;
			}
			else{
				dirname += ch;
			}
		}

		return "";
	}
 
}
