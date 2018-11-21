package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//import com.sun.corba.se.impl.protocol.FullServantCacheLocalCRDImpl;

public class Client {
//	Lista de los archivos disponibles en el pc actual
	private static ArrayList<String> myfiles;
//	Esta variable es quemada porque depende del sistema y la carpeta en donde esta el proyecto
	private static String path = "/root/Documents/GitHub/miniature-sniffle/src/data";
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Connection c = new Connection();
		
		
		String myip, ipserver;
		myip="";
		try {
			myip=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ipserver="localhost";
		// TODO Auto-generated method stub
		try {
			myfiles = new ArrayList<String>();
//			Se llena la lista de archivos con los archivos en nuestra carpeta
			fulllist();
			
//			Conexión al servidor.
			String servernameip ="rmi://"+ipserver+":5050/ABC";
			IRMIServer i = (IRMIServer)(Naming.lookup(servernameip));
			System.out.println("Lookup realizado con éxito.");
			
//			Se envía una lista de los archivos en el directorio.
			boolean t =i.ihave(myfiles, myip);
			if(t) {
				System.out.println("Envío exitoso de mi lista de archivos");
			}
			else {
				System.out.println("No se realizó el envío de la lista de archivos.");
			}
//			Lista de los archivos en el servidor disponibles para descarga
//			Deberíamos listar 'disposable' en nuestra GUI 
			ArrayList<String> disposable;
			disposable = i.getfiles();
			
			while(disposable.isEmpty()) {
				System.out.println("No hay archivos en el servidor, esperando actualizaciones.");
				disposable = i.getfiles();
				TimeUnit.SECONDS.sleep(3);
			}
			System.out.println("Hay archivos en el servidor");
			
			System.out.println("ARCHIVOS EN EL SERVIDOR: ");
			for (String string : disposable) {
				System.out.println(string);
			}
			
//			El usuario en la GUI selecciona un nombre (la variable 'selected')
			String selected ="file1";
			
//			El servidor devuelve la lista de peers disponibles para ese archivo
			ArrayList<String> peers;
			peers = i.iwant(selected);
			
			System.out.println("Peers con el archivo:");
			for (String string : peers) {
				System.out.println(string);
			}
			
//			PARTE 2 
			
			ArrayList<Thread>threads = new ArrayList<>();
			ArrayList<Thread>threads2 = new ArrayList<>();
			ArrayList<ConnectionAsk> asks = new ArrayList<>();
//			Por cada puerto se crea un hilo
			for (int j = 0; j < peers.size(); j++) {
//			Por cada puerto se ejecuta la clase ConnectionAsk que pregunta por las lineas
				ConnectionAsk ask = new ConnectionAsk(peers.get(j), selected);
				Thread tr = ask;
				tr.start();
//				Se agrega un ConnectionAsk a un arreglo para tener control sobre los datos
//				Uno de los atributos de la clase ConnectionAsk es una lista de enteros
				asks.add(ask);
				threads.add(tr);
			}
			// all threads are now started
			 
			// later...
			for (int j = 0; j < threads.size(); j++) {
				try{((Thread) threads.get(j)).join();}
				catch(InterruptedException e) 
				{
				System.err.println("Interrupted");
				}
			}
			
//			Después de que finalizan los hilos, el arreglo de ConnectionAsk mantiene los datos
			
			//Por cada una de las conexiones, se agrega al map la ip correspondiente a la lista de lineas.
			int cont = 0;
			HashMap<String, ArrayList<Integer>> list = new HashMap<String, ArrayList<Integer>>();
//			Se hace un mapa con la lista de lineas por cada ip
			for (ConnectionAsk connectionAsk : asks) {
				list.put(asks.get(cont).ip, asks.get(cont).lines);
				cont++;
			}
			
//			Se hace la repartición (NO ESTÁ HECHA :P)
			HashMap<String, Integer> toshare = share(list);
			
			
			ArrayList<ConnectionSend> sends = new ArrayList<ConnectionSend>();
			System.out.println("Aqui reparto descargas");
			for (String ips : toshare.keySet()) {
				ConnectionSend send = new ConnectionSend(selected, toshare.get(ips), ips);
				Thread t2 = send;
				sends.add(send);
				t2.start();
				threads2.add(t2);
			}

			// all threads are now started
			 
			// later...
			for (int j = 0; j < threads2.size(); j++) {
				try{((Thread) threads2.get(j)).join();}
				catch(InterruptedException e) 
				{
				System.err.println("Interrupted");
				}
			}
		
//				Se listan las lineas y se ordena el archivo (NO LO HICE :P)
			ArrayList<String> strings = new ArrayList<String>();
			System.out.println("Aqui unifico archivo");
			for (ConnectionSend connectionSend : sends) {
				strings.add(connectionSend.result);
			}
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fulllist() {
		try {
			Files.walk(Paths.get(path)).forEach(ruta-> {
			    if (Files.isRegularFile(ruta)) {
			        String line = ruta.toString();
			        String[] lines = line.split("/");
			        line = lines[lines.length-1];
			    	myfiles.add(line);
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Lista de los archivos en la base del cliente
//		Se leen los archivos de la carpeta y se llena el arraylist
	}
//	
	public static HashMap<String, Integer >share( HashMap<String, ArrayList<Integer> > hash ){
		HashMap<String, Integer > hashi = new HashMap<String, Integer >();
		String menor = "";
		int menorint = 999;
		HashMap<Integer, Boolean > assigned = new HashMap<Integer, Boolean>();
		for (String string : hash.keySet()) {
			for(Integer j : hash.get(string)) {
				assigned.put(j, false);
			}
		}
		boolean allassigned =false;
		while(!allassigned) {
			for (String string : hash.keySet()) {
				if(hash.get(string).size() < menorint) {
					menorint = hash.get(string).size();
					menor = string;
				}
			}
			for(Integer linesm : hash.get(menor)) {
				hashi.put(menor, linesm);
			}
			hash.remove(menor);
		}
		return hashi;
	}
	
//	Este método recibe lineas con su índice y contenido Ej: '5-La violencia' y crea un archivo
	public static void order(ArrayList<String> lines) {
		
	}
}
