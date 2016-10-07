package Centrala;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICentrala extends Remote{
	boolean rejestruj(String nazwa, Object obj) throws RemoteException;
	boolean wyrejestruj(String nazwa) throws RemoteException;
}