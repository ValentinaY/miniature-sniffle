class SimpleThread2 extends Thread 
{  
private static int thread2Count = 0;  
private int thread2Number = ++thread2Count;  
public SimpleThread2() 
{ 
	System.out.println("Making " + thread2Number);  
}  
public void run() 
{
	System.out.println("Thread " + thread2Number + " Descargo linea");

}
}
