package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ConnectionAsk extends Thread{
	public ArrayList<Integer> lines;
	public ArrayList<String> ports;
	public String ip;
	public String file;
	public ConnectionAsk(String ip, String file) {
		lines = new ArrayList<Integer>();
		ports = new ArrayList<String>();
		ports.add("5051");
		ports.add("5052");
		ports.add("5053");
		ports.add("5054");
		ports.add("5055");
		this.ip=ip;
		this.file=file;
	}

	@Override
	public void run() {
		int cont =0;
		String servernameip ="rmi://"+ip+":"+ports.get(cont)+"/ABC";
		boolean connected =false;
		while(!connected) {
			try {
				IRMIClient i = (IRMIClient)(Naming.lookup(servernameip));
				lines=i.whichline(file);
				System.out.println("Lookup realizado con éxito.");
				connected = true;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cont++;
			if(cont == 5) {
				cont = 0;
			}
		}
	}

}
