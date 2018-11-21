import java.util.ArrayList;

class SimpleThread extends Thread 
{  
private int countDown = 5;  
private static int threadCount = 0;  
private int threadNumber = ++threadCount;  
private ArrayList<Integer>codigos = new ArrayList<>();
public SimpleThread(ArrayList<Integer>codes ) 
{ 
	this.codigos=codes;
	System.out.println("Making " + threadNumber);  
}  

public int getThreadNumber() {
	return threadNumber;
}

public void run() 
{
	addData();
	System.out.println("Thread " + threadNumber + " Pregunto linea");
	
	
	/*while(true) 
	{      
		System.out.println("Thread " + threadNumber + "(" + countDown + ")");
		try{
		sleep(10);
		}
		catch(InterruptedException e){
		System.err.println("Interrupted");
		}
		if(--countDown == 0) return;
	}*/
}

private void addData() {
	codigos.add(threadNumber);
}


}
