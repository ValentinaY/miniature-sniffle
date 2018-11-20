package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.util.ArrayList;
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
			
//			Por cada peer se hace un hilo
//			for (int j=0; j<peers.size(); j++) {
//				Un hilo
				
//			}
			
//			Y ES TODOOO
			
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
//	public static ArrayList<String> repartir(){
//		
//	}
}
