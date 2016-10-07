package Sensor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISensor extends Remote{
	//w 1/s
	void setup(int czestotliwosc,Object obj) throws RemoteException;
}