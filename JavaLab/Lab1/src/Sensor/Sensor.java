package Sensor;

import Centrala.ICentrala;
import static Commons.Commons.*;
import Tablica.ITablica;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by pas114 on 2016-10-06.
 */
public class Sensor extends UnicastRemoteObject implements ISensor{
    private String name;
    private IOdczytywanie odczytywanie;

    protected Sensor() throws RemoteException {
        super();
        odczytywanie = new Odczytywanie();
    }

    public static void main(String[] args) {

        try {
            Sensor sensor = new Sensor();
            sensor.setName(registerObject("S",sensor));
            if(null==sensor.getName())
                System.exit(-1);
            createGUI(sensor);
            sensor.odczytywanie.setName(sensor.getName());
            sensor.odczytywanie.run();

        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }


    }
    @Override
    public void setup(int czestotliwosc, Object obj) throws RemoteException {
        odczytywanie.setCzestotliwosc(czestotliwosc);
        if(obj==null || obj instanceof ITablica){
           odczytywanie.setTablica((ITablica)obj);
        }
        odczytywanie.setName(name);

    }

    private static void createGUI(Sensor sensor){
        JButton button;
        button = new JButton("Turn off and unregister Sensor : "+sensor.name);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        button.setMnemonic(KeyEvent.VK_ESCAPE);
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                wylacz(sensor);
            }
        });
        JFrame f = new JFrame(sensor.getName());
        f.add(button);
        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                wylacz(sensor);
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private static void wylacz(Sensor sensor){
        try {
            Registry registry = LocateRegistry.getRegistry(8091);
            ((ICentrala) registry.lookup("Centrala")).wyrejestruj(sensor.getName());
        }catch (RemoteException e){
            System.err.println(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
