class SimpleThread extends Thread 
{  
private int countDown = 5;  
private static int threadCount = 0;  
private int threadNumber = ++threadCount;  
public SimpleThread() 
{ 
	System.out.println("Making " + threadNumber);  
}  
public void run() 
{
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
}
