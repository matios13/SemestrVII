import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pas114 on 2016-10-20.
 */
public class Main {
    /**
     * Simple usage of the CustomClassLoader implementation
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */
    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException,
            InvocationTargetException
    {
        Panel panel = new Panel();

        MyClassLoader loader = new MyClassLoader();
        // This class should be in your application class path
        Class<?> c = loader.findClass("Test");
        panel.getClassChoser().addItem(c);
        System.out.println(c.getMethods());
        Class<?> c2 = loader.findClass("Test2");
        panel.getClassChoser().addItem(c2);
        Object o = c.newInstance();
        Method m = c.getMethod("toString");
        System.out.println(m.invoke(o));
        JFrame jFrame = new JFrame();
        jFrame.add(panel);
        jFrame.setLayout(new FlowLayout());
        jFrame.setVisible(true);
    }
}
