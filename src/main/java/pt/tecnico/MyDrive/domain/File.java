package pt.tecnico.MyDrive.domain;
import org.jdom2.Element;
import org.joda.time.DateTime;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.Exception.*;

public class File extends File_Base {


	protected File() {}

	public File(Integer id, String name,  String permissions) {
		super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		setPermissions(permissions);


	}


	public String getPath(){
		String path = "";
		File f = this;
		while(!((f.getName()).equals("/"))){
			path = "/" + f.getName() + path;
			//	System.out.println(f.getName());
			f = f.getDirectory();
		}
		return path;
	}

	public static boolean fileNameCheck(String name){
		for(char ch : name.toCharArray()){
			if(ch == '/' || ch == '.'){
				return false;
			}
		}
		return true;
	}


	public void remove(){
			this.remove();
			this.setUser(null);
			this.setDirectory(null);
			deleteDomainObject();

	}

}
