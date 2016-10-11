package Centrala;


import Sensor.ISensor;

/**
 * Created by Uzytkownik on 11.10.2016.
 */
public class SensorStatus {
    private String name;
    private ISensor sensor;
    private String tablica;
    private Integer czestotliwosc;

    public SensorStatus(String name , ISensor sensor){
        this.name=name;
        this.sensor=sensor;
    }

    @Override
    public boolean equals(Object o){
        if(o!=null && o instanceof SensorStatus && ((SensorStatus)o).name.equals(this.name)) {
            return true;
        }
        return false;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ISensor getSensor() {
        return sensor;
    }

    public void setSensor(ISensor sensor) {
        this.sensor = sensor;
    }

    public String getTablica() {
        return tablica;
    }

    public void setTablica(String tablica) {
        this.tablica = tablica;
    }

    public Integer getCzestotliwosc() {
        return czestotliwosc;
    }

    public void setCzestotliwosc(Integer czestotliwosc) {
        this.czestotliwosc = czestotliwosc;
    }
}
