package pt.tecnico.MyDrive.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.joda.time.DateTime;

import pt.tecnico.MyDrive.domain.User;

public class App extends App_Base {
	
    public App(Integer id,  String name, String owner, String method, String perm, String path){
        super();
        
        MyDrive md = MyDrive.getInstance();
        
        User u = User.getUserByUsername(owner);
        Directory dir = (Directory) Directory.getDirByPath(path, md.getRootdir());
        
        setId(id);
        setData(method);
        setLastModif(new DateTime());
        setDirectory(dir);
        setUser(u);
        setPermissions(perm);
    }

    
    public App(Integer id,  String name, String method, String perm){
        super();
        
        setId(id);
        setData(method);
        setLastModif(new DateTime());
        setPermissions(perm);
    }
    
    public String ReadApp(){
    	return this.getData();
    }
    
    public void WriteApp(String method){
    	this.setData(method);
    }
    
    public void Run() {
    	
    	//Object obj = null;
    	Method method;
    	
    	Class<?> c;
		try {
			c = Class.forName("pt.tecnico.MyDrive.domain.App");
			method = c.getDeclaredMethod ("Hello");
			method.invoke(this);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
    
    public void Hello(){
    	System.out.println("hello");
    }
}
