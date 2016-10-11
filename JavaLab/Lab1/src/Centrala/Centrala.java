package Centrala;

import Sensor.ISensor;
import Tablica.ITablica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static List<SensorStatus> sensor= new ArrayList<>();
    private static Map<String, ITablica> tablicaList = new HashMap<>();
    private static JTextArea logs = new JTextArea(30,30);
    private static JTextArea status = new JTextArea(30,30);
    private static JComboBox sensorComboBox = new JComboBox();
    private static JComboBox tablicaComboBox = new JComboBox();
    private static JTextField czestotliwosc = new JTextField();
    protected Centrala() throws RemoteException {

    }

    public static void main(String[] args) {
        try {
            String name = "Centrala";
            ICentrala centrala = new Centrala();
             Registry registry = LocateRegistry.createRegistry(8091);
            registry.rebind(name,centrala);
            System.out.println("Start");
            createGUI();
        } catch (Exception e) {
            System.err.println("Exception:");
            e.printStackTrace();
        }


    }
        @Override
    public boolean rejestruj(String nazwa, Object obj) throws RemoteException {
        if(obj instanceof ISensor && !sensor.contains(new SensorStatus(nazwa,null))){
            sensor.add(new SensorStatus(nazwa, (ISensor) obj));
            loguj("Udalo sie dodac Sensor: " + sensor.size());
            updateStatus();
            sensorComboBox.addItem(nazwa);
            return true;
        }else if (obj instanceof ITablica &&!tablicaList.containsKey(nazwa)){
            tablicaList.put(nazwa, (ITablica) obj);
            tablicaComboBox.addItem(nazwa);
            loguj("Udalo sie dodac tablice: " + tablicaList.size());
            return true;
        }
        return false;
    }

    @Override
    public boolean wyrejestruj(String nazwa) throws RemoteException {
        if(sensor.contains(new SensorStatus(nazwa,null))){
            sensor.remove(new SensorStatus(nazwa,null));
            sensorComboBox.removeItem(nazwa);
            loguj("Udalo sie usunac Sensor : " + nazwa + " zostalo : " +sensor.size());
            updateStatus();
            return true;
        }else if (tablicaList.containsKey(nazwa)){
            tablicaList.remove(nazwa);
            tablicaComboBox.removeItem(nazwa);
            sensor.stream().forEach(s->{
                if(s.getTablica().equals(nazwa)){
                    s.setTablica(null);
                    updateSensor(s);
                    updateStatus();
                }
            });
            loguj("Udalo sie usunac tablice: " + nazwa + " zostalo : " +tablicaList.size());
            return true;
        }
        return false;
    }

    private static void loguj(String message){
        System.out.println(message);
        logs.append(message+"\n");
    }

    private static void updateStatus(){
        status.setText("");
        sensor.stream().forEach((s) -> status.append(s.getName()+" ::  tablica : " +
            s.getTablica()+" | czestotliwosc : "+ s.getCzestotliwosc()+"\n")
            );
    }

    public static void updateSensor(SensorStatus sensorStatus){
        try {
            sensorStatus.getSensor().setup(sensorStatus.getCzestotliwosc(),tablicaList.get(sensorStatus.getTablica()));

        } catch (RemoteException e) {

            e.printStackTrace();
        }
    }
    private static void createGUI() {
        //KONFIGURACJA
        JPanel configuration = new JPanel();
        sensorComboBox.setVisible(true);
        sensorComboBox.setPreferredSize(new Dimension(75, 50));
        sensorComboBox.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Sensor"),
                                BorderFactory.createEmptyBorder(2,2,2,2)),
                        sensorComboBox.getBorder()));
        configuration.add(sensorComboBox,BorderLayout.WEST);
        tablicaComboBox.setVisible(true);
        tablicaComboBox.setPreferredSize(new Dimension(75, 50));
        tablicaComboBox.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Tablica"),
                                BorderFactory.createEmptyBorder(2,2,2,2)),
                        tablicaComboBox.getBorder()));
        configuration.add(tablicaComboBox,BorderLayout.CENTER);
        czestotliwosc.setVisible(true);
        czestotliwosc.setPreferredSize(new Dimension(75,50));
        czestotliwosc.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Czestotliwosc"),
                                BorderFactory.createEmptyBorder(2,2,2,2)),
                        czestotliwosc.getBorder()));
        configuration.add(czestotliwosc,BorderLayout.EAST);
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!czestotliwosc.getText().isEmpty()){
                    int index = sensor.indexOf(new SensorStatus((String)sensorComboBox.getSelectedItem(),null));
                    SensorStatus sensorStatus = sensor.get(index);
                    if(sensorStatus!=null){
                        sensorStatus.setCzestotliwosc(new Integer(czestotliwosc.getText()));
                        sensorStatus.setTablica((String)tablicaComboBox.getSelectedItem());
                        updateSensor(sensorStatus);
                        updateStatus();
                    }else loguj("nieznaleziono sensora");

                }else loguj("czestotliwosc jest pusta");
            }
        });
        save.setVisible(true);
        configuration.add(save,BorderLayout.EAST);
        //STATUS
        status.setVisible(true);
        status.setEditable(false);
        JScrollPane areaScrollStatusPane = new JScrollPane(status);
        areaScrollStatusPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollStatusPane.setPreferredSize(new Dimension(400, 200));
        areaScrollStatusPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Status"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                        areaScrollStatusPane.getBorder()));

        //KONF+STATUS
        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                configuration,areaScrollStatusPane);
        //LOGI
        logs.setVisible(true);
        logs.setEditable(false);

        JScrollPane areaScrollLogsPane = new JScrollPane(logs);
        areaScrollLogsPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollLogsPane.setPreferredSize(new Dimension(400, 100));
        areaScrollLogsPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Logs"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                        areaScrollLogsPane.getBorder()));

        //FRAME
        JFrame f = new JFrame("Centrala");
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                splitPane1,areaScrollLogsPane);
        f.add(splitPane);
        f.pack();
        f.setVisible(true);

    }
}
