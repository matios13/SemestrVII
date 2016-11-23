package Client;

import Intefrace.PawnMXBean;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.net.MalformedURLException;
import java.util.*;

import static Client.Main.size;
import static Intefrace.PawnMXBean.magnes;
import static Intefrace.PawnMXBean.square;
import static Intefrace.PawnMXBean.straight;

/**
 * Created by pas114 on 2016-11-03.
 */
public class JavaScriptLoader {

    private static String jmxServerURL = "service:jmx:rmi:///jndi/rmi://127.0.0.1:";
    private MBeanServerConnection server;
    private Map<String, String> loadedScripts;

    public List<String> getLoadedScriptsNames() {
        Set<String> keys = loadedScripts.keySet();
        return new ArrayList<>(keys);
    }

    public JavaScriptLoader() {
        loadScripts();
        String portString = ""+1199;
        jmxServerURL+=portString+"/jmxrmi";
        final JMXServiceURL jmxUrl;
        try {
            jmxUrl = new JMXServiceURL(jmxServerURL);
            final JMXConnector connector = JMXConnectorFactory.connect(jmxUrl);
            server = connector.getMBeanServerConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot connect to JMX shutting down");
            System.exit(0);
        }


    }

    private void loadScripts() {
        loadedScripts = new HashMap<>();
        loadedScripts.put("Magnes",magnes);
        loadedScripts.put("Square",square);
        loadedScripts.put("Straight",straight);
    }


    public void placePawn(List<Field> fields, Field field, String script) {

        try{
            final ObjectName objectName = new ObjectName(loadedScripts.get(script));
            final PawnMXBean pawn = JMX.newMBeanProxy(server,objectName,PawnMXBean.class);
            int[][] table = pawn.placePawn(tableFromListField(fields,size),field.getX(),field.getY());
            listFromListField(table,fields,size);
        }catch (Exception e){

        }
    }
    public int[][] tableFromListField(List<Field> fields, int size) {
        int[][] grid = new int[size][size];
        fields.stream().forEach(f->grid[f.getX()][f.getY()]=f.getFieldEnum().number);
        return grid;
    }

    public void listFromListField(int[][]grid,List<Field> fieldsList, int size) {
        fieldsList.stream().forEach(f->f.setFieldEnum(FieldEnum.getById( grid[f.getX()][f.getY()])));
    }



}
