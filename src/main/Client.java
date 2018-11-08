package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.util.ArrayList;

//import com.sun.corba.se.impl.protocol.FullServantCacheLocalCRDImpl;

public class Client {
//	Lista de los archivos disponibles en el pc actual
	private static ArrayList<String> myfiles;
//	Esta variable es quemada porque depende del sistema y la carpeta en donde esta el proyecto
	private static String path = "/root/eclipse-workspace/RMIProject/src/data";
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			Se llena la lista de archivos con los archivos en nuestra carpeta
			fulllist();
			
//			Conexión al servidor.
			String servernameip ="rmi://localhost:5050/ABC";
			IRMIServer i = (IRMIServer) (Naming.lookup(servernameip));
			
//			Se envía una lista de los archivos en el directorio.
			i.ihave(myfiles);
			
//			Lista de los archivos en el servidor disponibles para descarga
//			Deberíamos listar 'disposable' en nuestra GUI 
			ArrayList<String> disposable;
			disposable = i.getfiles();
			
			while(!disposable.isEmpty());
			
//			El usuario en la GUI selecciona un nombre (la variable 'selected')
			String selected ="";
			
//			El servidor devuelve la lista de peers disponibles para ese archivo
			ArrayList<Peer> peers;
			peers = i.iwant(selected);
			
//			Por cada peer se hace un hilo
			for (int j=0; j<peers.size(); j++) {
//				Un hilo
			}
			
//			Y ES TODOOO
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fulllist() {
		try {
			Files.walk(Paths.get(path)).forEach(ruta-> {
			    if (Files.isRegularFile(ruta)) {
			        myfiles.add(ruta.toString());
			    	System.out.println(ruta);
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Lista de los archivos en la base del cliente
//		Se leen los archivos de la carpeta y se llena el arraylist
	}
}
