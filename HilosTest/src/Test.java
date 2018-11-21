
import java.util.Map;

class Test{  
public static void main(String args[]){  
	Downloader d=new Downloader("Archivo1"); 
	
	for(int i=0;i<4;i++) {
		int j=i;
		Thread thread = new Thread(){  
		public void run(){
			d.pedir(j);
			}  
		};
		thread.start(); 
	
	}
	
	new Thread(){  
		public void run(){
			d.espera(4);
			}  
		}.start(); 
	
		for(Map.Entry m:d.lineas.entrySet()){    
		       System.out.println(m.getKey()+" "+m.getValue());    
		}  
}}

