package pt.tecnico.MyDrive.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.joda.time.DateTime;

public class App extends App_Base {

	public App(Integer id,  String name, String owner, String method, String perm, String path){
		super();

		MyDrive md = MyDrive.getInstance();

		User u = User.getUserByUsername(owner);
		Directory dir = (Directory) Directory.getDirByPath(path, md.getRootdir());

		setId(id);
		setName(name);
		setData(method);
		setLastModif(new DateTime());
		setDirectory(dir);
		setUser(u);
		setPermissions(perm);
	}


	public App(Integer id,  String name, String method, String perm){
		super();

		setName(name);
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

	public void Run(String[] args ) {

		String m="";
		String data = this.getData();
		String cl="";

		for(char ch : data.toCharArray()){
			if (ch=='.'){
				cl += m +".";
				m = "";
			}
			else{
				m += ch;
			}
		}

		String ncl=cl.substring(0,cl.length()-1);



		Method method;

		Class<?> c;
		try {

			c = Class.forName(ncl);

			method = c.getDeclaredMethod (m, String[].class);
			method.invoke(this, (Object)args);



		} catch (ClassNotFoundException e1) {

			try {

				c = Class.forName(data);
				method = c.getDeclaredMethod ("main", String[].class);
				method.invoke(this, (Object)args);

			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}




		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
