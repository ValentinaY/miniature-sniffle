package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class ImplementationRMIServer extends UnicastRemoteObject implements IRMIServer {

	private HashMap<String, ArrayList<String> > names = new HashMap<String, ArrayList<String>>();
	public ImplementationRMIServer() throws RemoteException{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String greet(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return "Hola "+name;
	}

	@Override
	public ArrayList<String> getfiles() throws RemoteException {
		ArrayList<String> files = new ArrayList<String>();
		System.out.println(names.size());
		names.forEach((k,v) -> files.add(k));
		return files;
	}

	@Override
	public boolean ihave(ArrayList<String> myfiles, String ip) throws RemoteException{
		boolean answer =false;
		for (String string : myfiles) {
			System.out.println("Recibido del cliente "+ip+" el archivo: ");
			System.out.println(string);
			if(!names.containsKey(string)) {
				names.put(string, new ArrayList<String>());
			}
			names.get(string).add(ip);
			answer = true;
		}
		return answer;
	}

	@Override
	public ArrayList<String> iwant(String name) throws RemoteException{		
		return names.get(name);
	}

}
