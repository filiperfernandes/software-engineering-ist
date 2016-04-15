package pt.tecnico.MyDrive.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.tecnico.MyDrive.Exception.*;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.Mydrive.service.dto.FileInfoDto;


public class ListDirectoryService extends MyDriveService{

	private long token;
	private List<FileInfoDto> listfiles;
	

	public ListDirectoryService(long token){

		this.token=token;



	}
	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive.getInstance();	
		Session s = getSessionByToken(token);
		token = s.getToken();
		Directory dir = s.getCurrentdir();

		listfiles = new ArrayList<FileInfoDto>();

		checkPermissionsRead(s.getUser(),dir.getUser(),dir.getPermissions());
		listfiles.add(new FileInfoDto(".", dir.getPermissions(), (dir.getUser()).getUsername(), dir.getId()));
		listfiles.add(new FileInfoDto("..", (dir.getDirectory()).getPermissions(),((dir.getDirectory()).getUser()).getUsername() , (dir.getDirectory()).getId()));

		for(pt.tecnico.MyDrive.domain.File f : dir.getFileSet()){

			listfiles.add(new FileInfoDto(f.getName() , f.getPermissions(), (f.getUser()).getUsername() ,f.getId()));
		}
		Collections.sort(listfiles);
	}

	public final List<FileInfoDto> result() {
		return listfiles;
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