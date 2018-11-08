package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ImplementationRMIServer extends UnicastRemoteObject implements IRMIServer {

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
	public ArrayList<String> getfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ihave(ArrayList<String> myfiles) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Peer> iwant(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
