package Tablica;

import Centrala.ICentrala;
import Sensor.Odczyt;
import Commons.Commons;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import static Commons.Commons.registerObject;
import static Commons.Commons.turnOff;

/**
 * Created by Uzytkownik on 07.10.2016.
 */
public class Tablica extends UnicastRemoteObject implements ITablica {
    private String name;
    private Map<String,String> readings;
    private static JTextArea textArea = new JTextArea(30,30);

    protected Tablica() throws RemoteException {
        readings = new HashMap<>();
    }

    public static void main(String[] args) {

        try {
            Tablica tablica = new Tablica();
            tablica.setName(registerObject("T",tablica));
            if(tablica.getName()==null){
                System.exit(-1);
            }
            createGUI(tablica);

        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }


    }

    @Override
    public void wyslij(Odczyt o, String name) throws RemoteException {
        StringBuilder wynik = new StringBuilder();
        wynik.append("Odczyt z sensora : " + name+"\n     | ");
        o.m.forEach((k,v)-> wynik.append(k+" : "+v+" | "));
        wynik.append("\n");
        System.out.print(wynik.toString());
        readings.put(name,wynik.toString());
        reloadList();
    }

    private static void createGUI(Tablica tablica) {
        JButton button;
        button = new JButton("Turn off tablica : " + tablica.name);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                turnOff(tablica.getName());
            }
        });
        textArea.setVisible(true);
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(400, 400));
        areaScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Plain Text"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                        areaScrollPane.getBorder()));
        JFrame f = new JFrame(tablica.getName());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                areaScrollPane,
                button);
        f.add(splitPane);
        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                turnOff(tablica.getName());
            }
        });
    }
    private void reloadList(){
        StringBuilder wyniki = new StringBuilder();
        readings.forEach((k, v)->wyniki.append(v+"\n"));
        textArea.setText(wyniki.toString());
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
