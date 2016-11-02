package classLoader;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

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
            InvocationTargetException {
        MyClassLoader loader = new MyClassLoader();
        MyPanel panel = new MyPanel(loader);
        JFrame jFrame = new JFrame();
        jFrame.add(panel);
        jFrame.setLayout(new FlowLayout());
        jFrame.setVisible(true);
    }
}
