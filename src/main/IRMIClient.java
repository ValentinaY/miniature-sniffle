package main;

import java.rmi.Remote;

public interface IRMIClient extends Remote{
	public String sendme(String name, int line);
}
