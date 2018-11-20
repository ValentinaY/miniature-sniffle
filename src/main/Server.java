package main;

import java.rmi.Naming;

public class Server {
	
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
