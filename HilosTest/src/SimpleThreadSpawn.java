import java.awt.List;
import java.util.ArrayList;

public class SimpleThreadSpawn 
{  
public SimpleThreadSpawn() 
{

}

static void ManejaHilos(int n) {
	ArrayList<Thread>threads = new ArrayList<>();
	ArrayList<Integer>codes = new ArrayList<>();
	ArrayList<Thread>threads2 = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	  Thread t = new SimpleThread(codes);
	  t.start();
	  threads.add(t);
	}
	// all threads are now started
	 
	// later...
	for (int i = 0; i < threads.size(); i++) {
		try{((Thread) threads.get(i)).join();}
		catch(InterruptedException e) 
		{
		System.err.println("Interrupted");
		}
	}
	
	for (int i = 0; i < codes.size(); i++) {
		System.out.println("Imprimo codigos indice: "+i+" con dato de valor: "+codes.get(i));
	}
	
	
	System.out.println("Aqui reparto descargas");
	for (int i = 0; i < n; i++) {
		  Thread t2 = new SimpleThread2();
		  t2.start();
		  threads2.add(t2);
		}
		// all threads are now started
		 
		// later...
		for (int i = 0; i < threads2.size(); i++) {
			try{((Thread) threads2.get(i)).join();}
			catch(InterruptedException e) 
			{
			System.err.println("Interrupted");
			}
		}
		
		System.out.println("Aqui unifico archivo");
	
}

public static void main(String[] args) 
{
	
	ManejaHilos(3);
	  


System.out.println("All Threads Started and ran. Exiting   ");    
}
}