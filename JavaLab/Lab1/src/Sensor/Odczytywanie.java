package Sensor;

import Tablica.ITablica;

import java.rmi.RemoteException;
import java.util.Random;

/**
 * Created by Uzytkownik on 07.10.2016.
 */
public class Odczytywanie implements Runnable {
    private volatile ITablica tablica;
    private volatile int czestotliwosc=1;
    private volatile boolean running = false;
    private String name;
    @Override
    public void run(){
        running=true;
        while (running) {
            Random generator = new Random();
            Odczyt odczyt = new Odczyt();
            odczyt.m.put("Cisnienie", generator.nextInt());
            odczyt.m.put("Temperatura", generator.nextInt());
            System.out.println("Odczyt z sensora : " + name);
            odczyt.m.forEach((k, v) -> System.out.println("      Odczyt : " + k + " o wartości : " + v));
            if (tablica != null) {
                try {
                    tablica.wyslij(odczyt, name);
                } catch (RemoteException e) {
                    System.err.println("NIE UDALO SIE POLACZYC");
                }
            }
            try {
                Thread.sleep(1000 / czestotliwosc);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    public void terminate(){
        running=false;
    }
    public boolean isRunning(){
        return running;
    }

    public ITablica getTablica() {
        return tablica;
    }

    public void setTablica(ITablica tablica) {
        System.err.println("ZMIENIONO TABLICE");
        this.tablica = tablica;
    }

    public int getCzestotliwosc() {
        return czestotliwosc;
    }

    public void setCzestotliwosc(int czestotliwosc) {
        System.err.println("ZMIENIONO CZESTOTLIWOSC");
        this.czestotliwosc = czestotliwosc;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
