package classLoader;

import javax.swing.*;
import java.lang.reflect.Field;

/**
 * Created by pas113 on 2016-10-27.
 */
public class FieldPanel extends JPanel {

    JTextField textField;

    int min;

    int max;

    String defaultValue;

    String name;

    Field field;

    public FieldPanel(int min, int max, String defaultValue, String name,Field field) {
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        this.name = name;
        this.field = field;

        textField = new JTextField(20);
        textField.setBorder(BorderFactory.createTitledBorder(name));
        textField.addActionListener(a-> System.out.println("TEST"));
        add(textField);
    }
    public Object setValueAndvalidate(){
        String name = field.getGenericType().toString();
        System.out.println(name);
        String value = textField.getText();
        if(value.isEmpty()&&!defaultValue.isEmpty()) {
            if (name.equals("boolean")) return Boolean.parseBoolean(value);
            if (name.equals("byte")) return Byte.parseByte(value);
            if (name.equals("short")) return Short.parseShort(value);
            if (name.equals("int")) return Integer.parseInt(value);
            if (name.equals("long")) return Long.parseLong(value);
            if (name.equals("float")) return Float.parseFloat(value);
            if (name.equals("double")) return Double.parseDouble(value);
            return value;
        }else{
            Number number=null;
            if (name.equals("boolean")){
                if(!value.isEmpty()) {
                    return Boolean.parseBoolean(value);
                }
                else{
                    System.err.println("Cant be empty");
                }
            }
            if (name.equals("byte")) number = Byte.parseByte(value);
            if (name.equals("short")) number = Short.parseShort(value);
            if (name.equals("int")) number = Integer.parseInt(value);
            if (name.equals("long")) number = Long.parseLong(value);
            if (name.equals("float")) number = Float.parseFloat(value);
            if (name.equals("double")) number = Double.parseDouble(value);
            if(number.intValue()>max||number.intValue()<min){
                System.err.println("Value need to be between : "+ min+" and "+max);
                return null;
            }
            return number;
        }
    }


}
