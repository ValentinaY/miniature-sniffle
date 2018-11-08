package main;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

	private HashMap<String, ArrayList<Peer> > names;
	
	public Server() {
		try {
			IRMIServer i= new ImplementationRMIServer();
			Naming.rebind("rmi://localhost:5050/ABC", i);
			System.out.println("Listening");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		 new Server();
	}

}
