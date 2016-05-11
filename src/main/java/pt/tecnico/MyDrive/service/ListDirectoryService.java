package pt.tecnico.MyDrive.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.tecnico.MyDrive.Exception.*;
import pt.tecnico.MyDrive.domain.*;
import pt.tecnico.MyDrive.service.MyDriveService;
import pt.tecnico.MyDrive.service.dto.FileInfoDto;


public class ListDirectoryService extends MyDriveService{

	private long token;
	private String path;
	private List<FileInfoDto> listfiles;


	public ListDirectoryService(long token, String p){

		this.token=token;
		this.path=p;

	}
	
	
	@Override
	protected void dispatch() throws MyDriveException {
		MyDrive md = MyDrive.getInstance();	
		Session s = getSessionByToken(token);
		token = s.getToken();
		Directory dir;
		if(path.equals("")){
			dir = s.getCurrentdir();
		}
		else{
			Directory rd = md.getRootdir();
			dir = Directory.getDirByPath(path, rd);
		}

		listfiles = new ArrayList<FileInfoDto>();

		checkPermissionsRead(s.getUser(),dir.getUser(),dir.getPermissions());
		listfiles.add(new FileInfoDto(".", dir.getPermissions(), (dir.getUser()).getUsername(), dir.getId()));
		listfiles.add(new FileInfoDto("..", (dir.getDirectory()).getPermissions(),((dir.getDirectory()).getUser()).getUsername() , (dir.getDirectory()).getId()));

		for(pt.tecnico.MyDrive.domain.File f : dir.getFileSet()){
			if(f instanceof Link){
				listfiles.add(new FileInfoDto(f.getName() + " -> " + ((Link) f).getData(), f.getPermissions(), (f.getUser()).getUsername() ,f.getId()));

			}

			else{
				listfiles.add(new FileInfoDto(f.getName() , f.getPermissions(), (f.getUser()).getUsername() ,f.getId()));
			}
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