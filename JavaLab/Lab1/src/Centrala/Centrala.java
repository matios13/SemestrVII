package Centrala;

import Sensor.ISensor;
import Tablica.ITablica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pas114 on 2016-10-06.
 */
public class Centrala extends  UnicastRemoteObject implements ICentrala{
    private Map<String, ISensor> sensorList = new HashMap<>();
    private Map<String, ITablica> tablicaList = new HashMap<>();

    protected Centrala() throws RemoteException {

    }

    public static void main(String[] args) {
        try {
            String name = "Centrala";
            ICentrala centrala = new Centrala();
             Registry registry = LocateRegistry.createRegistry(8091);
            registry.rebind(name,centrala);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }


    }
        @Override
    public boolean rejestruj(String nazwa, Object obj) throws RemoteException {
        if(obj instanceof ISensor && !sensorList.containsKey(nazwa)){
            sensorList.put(nazwa, (ISensor) obj);
            System.out.println("Udalo sie dodac Sensor: " + sensorList.size());
            return true;
        }else if (obj instanceof ITablica &&!tablicaList.containsKey(nazwa)){
            tablicaList.put(nazwa, (ITablica) obj);
            System.out.println("Udalo sie dodac tablice: " + tablicaList.size());
            sensorList.get("S2").setup(1,obj);
            sensorList.forEach((k, v) -> {
                try {
                    v.setup(1, obj);
                    System.out.println("Udało sie z "+k);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public boolean wyrejestruj(String nazwa) throws RemoteException {
        if(sensorList.containsKey(nazwa)){
            sensorList.remove(nazwa);
            System.out.println("Udalo sie usunac Sensor : " + nazwa + " zostalo : " +sensorList.size());
            return true;
        }else if (tablicaList.containsKey(nazwa)){
            tablicaList.remove(nazwa);
            System.out.println("Udalo sie usunac tablice: " + nazwa + " zostalo : " +tablicaList.size());
            return true;
        }
        return false;
    }
}
