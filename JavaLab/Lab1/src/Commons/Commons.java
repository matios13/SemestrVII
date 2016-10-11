package Commons;

import Centrala.ICentrala;

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
        while (!returnStatus) {
            try {
                Registry registry = LocateRegistry.getRegistry(PORT);
                returnStatus=((ICentrala) registry.lookup(CENTRALA)).wyrejestruj(name);
                if(returnStatus)
                    continue;
            } catch (RemoteException e) {
                System.err.println(e);
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
            System.exit(0);
            }

    }
}
