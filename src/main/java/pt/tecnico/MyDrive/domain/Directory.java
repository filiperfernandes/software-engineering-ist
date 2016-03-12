package pt.tecnico.MyDrive.domain;

import org.jdom2.Element;
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
	
	
	public Directory(MyDrive md, String name) {
		super();
		setName(name);
		setMydrive(md);
	}


	public Directory(MyDrive md, Element xml) {
		super();
		xmlImport(xml);
		setMydrive(md);
	}
	
	public File getFileByName(String name){

		System.out.println("a procurar ficheiro com o nome " + name + " no directorio " + this.getName());
		for (File file: this.getFileSet()){
			System.out.println("o directorio: " + this.getName() + " encontrou o ficheiro " + file.getName());
			if(file.getName().equals(name)){
				System.out.println("ficheiro encontrado");
				return file;
			}
		}
		System.out.println("returning null");
		return null;
	}
	
	
	public Directory getHome(){
		MyDrive md = MyDrive.getInstance();
		Directory home = (Directory) md.getRootdir().getFileByName("home");
		System.out.println("O HOME E:" + home);
		return home;
	}
	
	
	public File getFileByID(String id){

		MyDrive md = MyDrive.getInstance();
		for (File file: md.getRootdir().getFileSet()){
			String x = String.valueOf(file.getId());
			if(x.equals(id)){
				return file;
			}
		}
		System.out.println("returning null");
		return null;
	}
	
	
	public void xmlImport(Element personElement) /*throws ImportDocumentException */{
		DateTime date = new DateTime();

		MyDrive md = MyDrive.getInstance();

		// clear current phone book | current mydrive
		/*for (File u: ((Directory) md).getFileSet()){
			System.out.println("vai remover " + u);
			md.removeUser(u);
			System.out.println("removeu user "+ u);
		}ainda falta por isto a funcionar*/
		
		
		
		String sid = personElement.getAttribute("id").getValue();
		
		Integer id = Integer.parseInt(sid);

		String name = personElement.getChildText("name");

		String owner = personElement.getChildText("owner");
		
		String perm = personElement.getChildText("perm");
		
		System.out.println("sid: "+sid + " id: "+id + " name: "+name + " owner: "+owner+ " perm: "+perm);
		Directory toAdd = new Directory (id, name, date, perm);
		
		//Directory dir = md.getRootdir();
		
		System.out.println("-----vamos procurar a pasta home no directorio ");
		
		//Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		//System.out.println("home = "+home + " rootdir= "+md.getRootdir());
		//System.out.println();
		Directory userDir = (Directory) getHome().getFileByName(owner);
		
		//home.addFile(userDir);
		
		userDir.addFile(toAdd);
		
	}
    
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