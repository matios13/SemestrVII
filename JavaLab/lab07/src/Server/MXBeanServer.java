package Server;

import Server.impl.MagnesPawn;
import Server.impl.SquarePawn;
import Server.impl.StraightPawn;

import javax.management.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

import static Intefrace.PawnMXBean.magnes;
import static Intefrace.PawnMXBean.square;
import static Intefrace.PawnMXBean.straight;

/**
 * Created by matio_000 on 21.11.2016.
 */
public class MXBeanServer {

    public static void registerMBean(
            final MBeanServer mbs,
            final String mBeanObjectName,
            final Class mBeanClass) {
        try {
            final ObjectName name = new ObjectName(mBeanObjectName);
            final Object mBean = mBeanClass.newInstance();
            mbs.registerMBean(mBean, name);
        } catch (InstantiationException badInstance)   // Class.newInstance()
        {
            System.err.println("Unable to instantiate provided class with class "
                    + "name " + mBeanClass.getName() + ":\n"
                    + badInstance.getMessage());
        } catch (IllegalAccessException illegalAccess)   // Class.newInstance()
        {
            System.err.println("Illegal Access trying to instantiate "
                    + mBeanClass.getName() + ":\n"
                    + illegalAccess.getMessage());
        } catch (MalformedObjectNameException badObjectName) {
            System.err.println(mBeanObjectName + " is a bad ObjectName:\n"
                    + badObjectName.getMessage());
        } catch (InstanceAlreadyExistsException duplicateMBeanInstance) {
            System.err.println(mBeanObjectName + " already existed as an MBean:\n"
                    + duplicateMBeanInstance.getMessage());
        } catch (MBeanRegistrationException mbeanRegistrationProblem) {
            System.err.println("ERROR trying to register " + mBeanObjectName + ":\n"
                    + mbeanRegistrationProblem.getMessage());
        } catch (NotCompliantMBeanException badMBean) {
            System.err.println("ERROR: " + mBeanObjectName + " is not compliant:\n"
                    + badMBean.getMessage());
        }
    }

    public static void main(final String[] arguments) {
        final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        registerMBean(mbs, magnes, MagnesPawn.class);
        registerMBean(mbs, square, SquarePawn.class);
        registerMBean(mbs, straight, StraightPawn.class);
        if (System.getProperty("com.sun.management.jmxremote") == null) {
            System.out.println("JMX remote is disabled");
        }
        String portString = System.getProperty("com.sun.management.jmxremote.port");
        if (portString != null) {
            System.out.println("JMX running on port "
                    + Integer.parseInt(portString));
        }
        readWait();
    }

    private static void readWait() {
        System.out.println("Press ENTER to exit.");
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        try {
            String username = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
