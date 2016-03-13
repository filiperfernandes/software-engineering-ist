package pt.tecnico.MyDrive.domain;

import org.joda.time.DateTime;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.Exception.*;


public class PlainFile extends PlainFile_Base {

	protected PlainFile() {}

	public PlainFile(Integer id,  String name, String permissions, String data) {
		super();
		setId(id);
		setName(name);
		setLastModif(new DateTime());
		setPermissions(permissions);
		setData(data);
	}

/*	public void readPlainFile(String path) {
		MyDrive md = MyDrive.getInstance();
		String dirname = "";
		Directory dir = md.getRootdir() ;
		Integer c = 0;
		for(char ch : path.toCharArray()){
			if(c!=null){
				if(c.equals(0)){
					c++;
				}
				else if(ch == '/'){
					dir = (Directory) (dir.getFileByName(dirname));
					dirname="";
				}
				else{
					dirname += ch;
				}
			}
			else{
				break;
			}
			System.out.println(((PlainFile) (dir.getFileByName(dirname))).getData());
		}
	}*/

}
