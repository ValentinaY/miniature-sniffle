package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ConnectionSend extends Thread{
	public ArrayList<String> ports;
	public String file;
	public String ip;
	public int line;
	public String result;
	public ConnectionSend(String file, int line, String ip) {
		ports = new ArrayList<String>();
		ports.add("5051");
		ports.add("5052");
		ports.add("5053");
		ports.add("5054");
		ports.add("5055");
		this.line=line;
		this.file=file;
		this.ip = ip;
	}

	@Override
	public void run() {
		int cont =0;
		String servernameip ="rmi://"+ip+":"+ports.get(cont)+"/ABC";
		boolean connected =false;
		while(!connected) {
			try {
				IRMIClient i = (IRMIClient)(Naming.lookup(servernameip));
				result= i.sendme(file, line);
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
