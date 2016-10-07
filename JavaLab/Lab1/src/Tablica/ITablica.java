package Tablica;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Sensor.Odczyt;


public interface ITablica extends Remote {
	void wyslij (Odczyt o, String name) throws RemoteException;
}




