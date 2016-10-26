import groovy.lang.GroovyShell;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by pas114 on 2016-10-20.
 */
public class Panel extends JPanel {
    private JPanel topPanel ;
    private JPanel filePanel;
    private JPanel loadPanel;
    private JPanel bottomPanel = new JPanel();
    private JComboBox<Class> classChoser;
    private JComboBox<Method> methodChoser;
    private ArrayList<JTextField> values = new ArrayList<>();
    private Object returnValue;
    private Object selectedClass;
    private Parameter[] parameters;
    MyClassLoader loader;

    public Panel(MyClassLoader loader) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        createFilePanel();
        createLoadPanel();
        createTopPanel();
        createBottomPanel();
        add(topPanel);
        add(bottomPanel);
        this.loader=loader;

    }

    private void createFilePanel(){

        filePanel = new JPanel();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addActionListener(a-> {
            File file =fileChooser.getSelectedFile();
            load(file);
        });
        filePanel.setLayout(new FlowLayout());
        filePanel.add(fileChooser);
        add(filePanel);
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
        classChoser.addActionListener(a -> updateMethodsForClass((Class) classChoser.getSelectedItem()));
        methodChoser = new JComboBox();
        methodChoser.addActionListener(a-> {
            if(methodChoser.getSelectedItem()!=null){
                setFields(((Method)methodChoser.getSelectedItem()).getParameters());
            }
        });
        topPanel.setLayout(new FlowLayout());
        topPanel.add(classChoser);
        topPanel.add(methodChoser);

    }

    private void createBottomPanel(){

        bottomPanel = new JPanel();
        values.stream().forEach(v->bottomPanel.add(v));
        JButton invokeButton = new JButton("INVOKE");
        System.out.println();
        invokeButton.addActionListener(a -> System.out.println(invokeMethod((Method) methodChoser.getSelectedItem())));
        bottomPanel.add(invokeButton);
        bottomPanel.setLayout(new FlowLayout());

    }
    private void updateBottomPanel(ArrayList<JTextField> fields){
        values.stream().forEach(v->bottomPanel.remove(v));
        values=fields;
        values.stream().forEach(v->{
            v.setVisible(true);
            bottomPanel.add(v);
        });
        bottomPanel.updateUI();
    }

    public JComboBox<Class> getClassChoser() {
        return classChoser;
    }

    public void setClassChoser(JComboBox<Class> classChoser) {
        this.classChoser = classChoser;
    }

    public JComboBox<Method> getMethodChoser() {
        return methodChoser;
    }

    public void setMethodChoser(JComboBox<Method> methodChoser) {
        this.methodChoser = methodChoser;
    }

    public Object invokeMethod(Method m) {
        try {
            if(values.isEmpty()){
                return m.invoke(selectedClass);
            }else{

                return m.invoke(selectedClass,createListOFParameters( values.stream().map(JTextField::getText).collect(Collectors.toList())));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "null";
    }
    private Object[] createListOFParameters(java.util.List<String> params){
        ArrayList<Object> returnParam= new ArrayList<>();
        for (int i = 0; i < params.size(); i++) {
            returnParam.add(setValue(params.get(i),parameters[i].getParameterizedType().getTypeName()));
        }
        return returnParam.toArray();
    }



    public void setFields(Parameter[] parameters){
        this.parameters = parameters;
        ArrayList<JTextField> fields= new ArrayList<>();
        Arrays.stream(parameters).forEach(p->{
            JTextField field = new JTextField(p.getName());
            field.setBorder(BorderFactory.createTitledBorder(p.getParameterizedType().getTypeName()));
            field.setPreferredSize(new Dimension(100,50));
            fields.add(field);
        });
        updateBottomPanel(fields);
    }
    public void updateMethodsForClass(Class c){
        if(c==null) {
            methodChoser.removeAllItems();
            return;
        }
        try {
            selectedClass=c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        methodChoser.removeAllItems();
        Arrays.stream(c.getMethods()).forEach(m->{
            methodChoser.addItem(m);
            System.out.println(m);
        });
        methodChoser.setSelectedIndex(0);

    }
    private Object setValue(String value,String name) {
        if (name.equals("String")) return value;
        if(name.equals("boolean") ) return Boolean.parseBoolean( value );
        if( name.equals("byte") ) return Byte.parseByte( value );
        if(name.equals("short") ) return Short.parseShort( value );
        if( name.equals("int") ) return Integer.parseInt( value );
        if( name.equals("long") ) return Long.parseLong( value );
        if( name.equals("float") ) return Float.parseFloat( value );
        if( name.equals("double") ) return Double.parseDouble( value );
        return null;
    }
    private void unload(Class classToUload){
        classChoser.removeItem(classToUload);
        loader.unload(classToUload);
    }
    private void load(File file){
        try {
            Class c =loader.findClassFromFile(generateFileName(file),generateURL(file));
            classChoser.addItem(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void load(String name){
        try {
            Class c =loader.findClass(name);
            classChoser.addItem(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String generateFileName(File file){
        String name = "";
        String[] path = file.getAbsolutePath().split("\\\\");
        ArrayList<String> packages = new ArrayList<String>();
        for (int i = path.length - 1; i >= 0; i--) {
            if(path[i].equals("bin"))
                break;
            String[] temp = path[i].split("\\.");
            if(temp.length>0)
                packages.add(temp[0]);
            else
                packages.add(path[i]);
        }
        for (int i = packages.size() - 1; i >= 0; i--) {
            name +=packages.get(i);
            if(i>0)
                name+=".";
        }
        System.out.println(name);
        return name;
    }
    private URL generateURL(File file){
        String name = "";
        String[] path = file.getAbsolutePath().split("\\\\");
        for (int i = 0; i < path.length; i++) {

            name+=path[i]+"\\";
            if(path[i].equals("bin"))
                break;
        }
        try {
            return new File(name).toURL();
        } catch (MalformedURLException e) {
            return null;
        }

    }
}
