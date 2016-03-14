package pt.tecnico.MyDrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;

import pt.tecnico.MyDrive.MyDriveApplication;

public class Directory extends Directory_Base {
    
	public Directory( Integer id, String name, DateTime lastModif, String permissions) {
        super();
        setId(id);
        setName(name);
        setLastModif(lastModif);
        setPermissions(permissions);
        setDimension(2);
        
        //setUser();
    }
	
	//XML export constructor
	public Directory(MyDrive md, String name) {
		super();
		setName(name);
		setMydrive(md);
	}

	//XML import constructor
	public Directory(MyDrive md, Element xml) {
		super();
		xmlImport(xml);
		setMydrive(md);
	}
	
	//Return File by name
	public static File getFileByName(String name){
		MyDrive md = MyDrive.getInstance();
		for (File file: md.getRootdir().getFileSet()){
			System.out.println(" encontrou o ficheiro " + file.getName());
			if(file.getName().equals(name)){
				System.out.println("ficheiro encontrado");
				return file;
			}
		}
		System.out.println("returning null");
		return null;
	}
	
	//Not working...
	public Directory getHome(){
		MyDrive md = MyDrive.getInstance();
		md.getRootdir();
		Directory home = (Directory) Directory.getFileByName("home");
		System.out.println("Dir O HOME E:" + home);
		return home;
	}
	
	//Return File by ID
	public File getFileByID(String id){

		MyDrive md = MyDrive.getInstance();
		for (File file: this.getFileSet()){
			String x = String.valueOf(file.getId());
			if(x.equals(id)){
				return file;
			}
		}
		System.out.println("returning null");
		return null;
	}
	
	
	
	//Import XML Directory
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
		//Directory toAdd = new Directory (id, name, date, perm);
		
		
		System.out.println("-----vamos procurar a pasta home no directorio ");
		
		//Directory home = (Directory) (md.getRootdir()).getFileByName("home");
		//System.out.println("home = "+home + " rootdir= "+md.getRootdir());
		//System.out.println();
		
		
		
		//Directory userDir = (Directory) getHome().getFileByName(owner);
		getHome();
		
		//MyDriveApplication.acabaAi(owner, id, name, date, perm);
		System.out.println("aqui e nao e pouco");
		//home.addFile(userDir);
		
		//userDir.addFile(toAdd);
		
	}

	//Export XML Directory
	public Element xmlExport() {
        Element dirElement = new Element("dir");
        dirElement.setAttribute("id", String.valueOf(getId()));
        
        //Path
        Element dirPath = new Element("path");
        dirPath.setText(getPath());
        dirElement.addContent(dirPath);

        //Name
        Element dirName = new Element("name");
        dirName.setText(getName());
        dirElement.addContent(dirName);
        
        //Owner
        Element dirOwner = new Element("owner");
        dirOwner.setText(getUser().getName());
        dirElement.addContent(dirOwner);
        
        //Perm
        Element dirPerm = new Element("perm");
        dirPerm.setText(getPermissions());
        dirElement.addContent(dirPerm);
        
        
        return dirElement;
    }
    
}