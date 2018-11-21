import java.util.HashMap;
import java.util.Scanner;

class Downloader {
	String name;
	HashMap<Integer,String> lineas;
	Downloader(String nombre){
		name=nombre;
		lineas= new HashMap <Integer,String>();
	}
	
	 
	synchronized void pedir(int cliente){ 
		String aux=whichLine(name);
		lineas.put(cliente, aux);
		System.out.println("Cliente: "+cliente+" lineas: "+aux);
		
	}
	synchronized void espera(int num){ 
		for(int i=0;i<4;i++) {
			System.out.println("Espero");
			try{wait();}catch(Exception e){} 
		}
		System.out.println("Fin");
	}
	
	String whichLine(String name) {
		System.out.println("Inserte lineas que posea:");
		Scanner scan = new Scanner(System.in);
		String myLine = scan.nextLine();
		return myLine;
	}
}