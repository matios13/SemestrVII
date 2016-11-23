package Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pas114 on 2016-11-03.
 */
public class GameGrid extends JPanel {

    private int counter = 0;
    private final int maxCounter = 3;
    private List<Field> grid;
    private JavaScriptLoader javaScriptLoader;
    private int size;

    public GameGrid(int size) {
        this.size = size;
        javaScriptLoader = new JavaScriptLoader();
        createGame();
    }
    private void createGame(){
        createRandomGrid(size);
        this.setLayout(new GridLayout(size, size, 1, 1));
        this.setVisible(true);
        drawGrid();
    }

    private void createRandomGrid(int size) {
        grid = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            grid.addAll(generateRandomFields(i, size));
        }
    }

    private List<Field> generateRandomFields(int y, int size) {
        Random random = new Random();
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            FieldEnum fEnum = random.nextInt(100) > 80 ? FieldEnum.BLOCKED : FieldEnum.FREE;
            fields.add(new Field(i, y, fEnum));
        }
        return fields;
    }

    private void drawGrid() {
        this.removeAll();
        grid.stream().forEach(f -> this.add(createComponent(f)));
        this.updateUI();
    }

    private Component createComponent(Field field) {
        JButton jButton = new JButton(field.getFieldEnum().toString());
        if (field.getFieldEnum().equals(FieldEnum.TAKEN)) {
            jButton.setIcon(UIManager.getIcon("FileView.computerIcon"));
            jButton.setEnabled(false);
            return jButton;
        }
        if (field.getFieldEnum().equals(FieldEnum.BLOCKED)) {
            jButton.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
            jButton.setEnabled(false);
            return jButton;
        }
        if (field.getFieldEnum().equals(FieldEnum.COVERED)) {
            jButton.setIcon(UIManager.getIcon("OptionPane.questionIcon"));
            jButton.setEnabled(false);
            return jButton;
        }
        jButton.addActionListener(a -> addPawn(field));
        return jButton;

    }

    private void addPawn(Field field) {
        JComboBox scripts = new JComboBox(javaScriptLoader.getLoadedScriptsNames().toArray());
        scripts.addItem("NONE");
        scripts.setSelectedIndex(scripts.getItemCount() - 1);
        scripts.setEditable(false);
        JOptionPane.showMessageDialog(null, scripts, "select or type a value", JOptionPane.QUESTION_MESSAGE);
        if (!scripts.getSelectedItem().equals("NONE")) {
            System.out.println(field.toString());
            javaScriptLoader.placePawn(grid, field, scripts.getSelectedItem().toString());
            field.setFieldEnum(FieldEnum.TAKEN);
            System.out.println(scripts.getSelectedItem());
            counter++;
            drawGrid();
            if (counter >= maxCounter) {
                long covered = grid.stream().filter(f -> f.getFieldEnum().equals(FieldEnum.COVERED)).count();
                int percent = (int) (covered*100/(size*size));
                int dialogResult = JOptionPane.showConfirmDialog(null,"Congratulations!\n you covered "+covered+" / "+size*size+" fields\n" +
                        "This is " +percent +"%\n\n NEW GAME ?");
                if(dialogResult == JOptionPane.YES_OPTION) {
                    createGame();
                }else{
                    System.exit(0);
                }
            }
        }

    }


}
