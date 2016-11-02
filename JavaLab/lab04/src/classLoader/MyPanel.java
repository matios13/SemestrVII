package classLoader;

import addnotations.Form;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by pas114 on 2016-10-20.
 */
public class MyPanel extends JPanel {
    private JPanel topPanel ;
    private JPanel loadPanel;
    private JPanel bottomPanel = new JPanel();
    private JComboBox<Class> classChoser;
    private ArrayList<JTextField> values = new ArrayList<>();
    private Object selectedClass;
    List<FieldPanel> fields;
    MyClassLoader loader;

    public MyPanel(MyClassLoader loader) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        createLoadPanel();
        createTopPanel();
        createBottomPanel();
        add(topPanel);
        add(bottomPanel);
        this.loader=loader;

    }
    private void createLoadPanel(){
        loadPanel = new JPanel();
        JTextField classField = new JTextField();
        classField.setPreferredSize(new Dimension(300,30));
        JButton button = new JButton("LOAD");
        button.addActionListener(a-> load(classField.getText()));
        loadPanel.add(classField);
        loadPanel.add(button);
        add(loadPanel);
    }

    private void createTopPanel(){
        JButton unload = new JButton("UNLOAD");
        unload.addActionListener(a -> {
            if(classChoser.getSelectedItem()!=null){
                unload((Class)classChoser.getSelectedItem());
            }
        });
        topPanel = new JPanel();
        topPanel.add(unload);
        classChoser = new JComboBox();
        classChoser.addActionListener(a -> updatePropertiesForClass((Class) classChoser.getSelectedItem()));
        topPanel.setLayout(new FlowLayout());
        topPanel.add(classChoser);

    }

    private void createBottomPanel(){

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
        values.stream().forEach(v->bottomPanel.add(v));
        JButton invokeButton = new JButton("SAVE");
        invokeButton.addActionListener(a -> saveObject());
        bottomPanel.add(invokeButton);

    }
    private void updateBottomPanel(List<FieldPanel> fields){
        this.fields=fields;
        System.out.println("THIS FIELDS : "+this.fields.size()+" fields : "+fields.size());
        bottomPanel.removeAll();
        fields.stream().forEach(f->bottomPanel.add(f));
        JButton invokeButton = new JButton("SAVE");
        invokeButton.addActionListener(a -> saveObject());
        bottomPanel.add(invokeButton);
        bottomPanel.updateUI();

    }
    private void saveObject(){

        if(fields.stream().allMatch(f -> {
            try {
                if(f.setValueAndValidate(selectedClass)==null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        })){
            System.out.println(selectedClass.toString());
            serializeObject(selectedClass);
            updatePropertiesForClass((Class) classChoser.getSelectedItem());

        }

    }



    public void updatePropertiesForClass(Class c){
        try {
            selectedClass = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fieldsTable= c.getFields();
        System.out.println("JEST" +fieldsTable.length);

        List<Field> annotatedFields = Arrays.stream(fieldsTable).
                filter(f->f.isAnnotationPresent(Form.class)).collect(Collectors.toList());
        System.out.println("JEST" +annotatedFields.size());
        List<FieldPanel> fields = annotatedFields.stream().map(f->{
            Form form = f.getAnnotation(Form.class);
            FieldPanel fieldPanel = new FieldPanel(form.min(),form.max(),form.defaultValue(),form.name(),f);
            return fieldPanel;
        }).collect(Collectors.toList());
        System.out.println("JEST" +fields.size());
        updateBottomPanel(fields);

    }
    private void unload(Class classToUload){
        classChoser.removeItem(classToUload);
        loader.unload(classToUload);
    }
    private void load(String name){
        try {
            Class c =loader.findClass(name);
            classChoser.addItem(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void serializeObject(Object object){
        try {
            String filename = JOptionPane.showInputDialog(this,
                    "Name of the file to serialize that object ?", null)+".ser";
            if(filename==null)
                return;
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "+filename);
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

}
