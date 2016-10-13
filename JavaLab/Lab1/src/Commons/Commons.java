package Commons;

import Centrala.ICentrala;
import Sensor.Sensor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by matio_000 on 11.10.2016.
 */
public class Commons {
    public static String CENTRALA="Centrala";
    public static Integer PORT=8091;
    public static void turnOff(String name) {
        boolean returnStatus = false;
        try {
            while (!returnStatus) {
                Registry registry = LocateRegistry.getRegistry(PORT);
                returnStatus = ((ICentrala) registry.lookup(CENTRALA)).wyrejestruj(name);
            }
        } catch (RemoteException e) {
            System.err.println(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        System.exit(0);

    }
    public static String registerObject(String name,Object obj) throws RemoteException, NotBoundException {
        String registeredName = null;
        Registry registry = LocateRegistry.getRegistry(Commons.PORT);
        ICentrala centrala = (ICentrala) registry.lookup(Commons.CENTRALA);
        for(int i=1;i<100;i++){
            registeredName=name+i;
            if(centrala.rejestruj(registeredName,obj))
                return registeredName;
        }
        return null;
    }
}
