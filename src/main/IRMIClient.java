package main;

import java.rmi.Remote;
import java.util.ArrayList;

public interface IRMIClient extends Remote{
	public String sendme(String name, int line);
	public ArrayList<Integer> whichline(String name);
}
