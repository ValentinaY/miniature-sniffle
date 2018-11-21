package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Connection extends Thread{
	public Connection() {
		
	}

	@Override
	public void run() {
		try {
			IRMIClient i= new ImplementationRMIClient();
			Naming.rebind("rmi://localhost:5051/ABC", i);
			System.out.println("Listening");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
