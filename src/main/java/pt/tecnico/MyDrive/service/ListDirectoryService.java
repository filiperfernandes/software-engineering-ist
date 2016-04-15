package pt.tecnico.MyDrive.service;


import pt.tecnico.MyDrive.Exception.*;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.MyDrive.service.ChangeDirectoryService;
import pt.tecnico.MyDrive.MyDriveApplication;


public class ListDirectoryService extends MyDriveService{
	
	private long token;


	public void ListDirectoryService(long token){

		this.token=token;

		
			
	}
	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();	
		Session s = getSessionByToken(token);
		token = s.getToken();
		Directory dir = s.getCurrentdir();

		checkPermissionsRead(s.getUser(),dir.getUser(),dir.getPermissions());
			System.out.println(".\n..");
			for(pt.tecnico.MyDrive.domain.File f : dir.getFileSet()){
				Integer c = 2;
				if( f instanceof PlainFile ){
					c=1;
				}
				else{
					for(pt.tecnico.MyDrive.domain.File fi : ((Directory) f).getFileSet()){
						c++;

					}
				}
				System.out.println(f.getName() + "      " + (f.getUser()) + "      " +f.getPermissions()+ "      " + c);
			}
	}
	private Session getSessionByToken(long token){
		MyDrive md = MyDrive.getInstance();

		for(Session s : md.getSessionSet()){
			if(s.getToken()==token){
				return s;
			}
		}
		throw new SessionDoesNotExistException(token);
	}
}