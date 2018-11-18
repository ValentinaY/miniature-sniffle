package main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRMIServer extends Remote {
	public String greet(String name) throws RemoteException;
	public ArrayList<String> getfiles() throws RemoteException;
	public boolean ihave(ArrayList<String> myfiles, String ip) throws RemoteException;
	public ArrayList<String> iwant(String name) throws RemoteException;
}
