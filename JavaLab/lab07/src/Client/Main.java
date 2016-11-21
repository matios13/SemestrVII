package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pas114 on 2016-11-03.
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(500,500));
        GameGrid gameGrid = new GameGrid(10);
        frame.add(gameGrid);
        frame.setVisible(true);
    }
}
