package Sensor;

import Tablica.ITablica;

/**
 * Created by Uzytkownik on 13.10.2016.
 */
public interface IOdczytywanie extends Runnable {
    public String getName();
    public void setName(String name);
    public void setTablica(ITablica tablica);

    public int getCzestotliwosc();

    public void setCzestotliwosc(int czestotliwosc);
}
