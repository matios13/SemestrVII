package classLoader;

import javax.swing.*;
import java.lang.reflect.Field;

/**
 * Created by Mateusz.Manka@eclipsegroup.co.uk on 2016-10-27.
 */
public class FieldPanel extends JPanel {

    JTextField textField;

    int min;

    int max;

    String defaultValue;

    String name;

    Field field;

    public FieldPanel(int min, int max, String defaultValue, String name, Field field) {
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        this.name = name;
        this.field = field;

        textField = new JTextField(20);
        textField.setBorder(BorderFactory.createTitledBorder(field.getGenericType().toString() + " " + name));
        add(textField);
    }

    public Object setValueAndValidate(Object object) throws IllegalAccessException {
        String type = field.getGenericType().toString();
        Number number = new Integer(0);
        try {
            String value = textField.getText();
            if (value.isEmpty() && !defaultValue.isEmpty()) {
                if (type.equals("boolean")) {
                    field.set(object, Boolean.parseBoolean(defaultValue));
                    return Boolean.parseBoolean(defaultValue);
                }
                if (type.equals("byte")) {
                    number = Byte.parseByte(defaultValue);
                }
                if (type.equals("short")) {
                    number = Short.parseShort(defaultValue);
                }
                if (type.equals("int")) {
                    number = Integer.parseInt(defaultValue);
                }
                if (type.equals("long")) {
                    number = Long.parseLong(defaultValue);
                }
                if (type.equals("float")) {
                    number = Float.parseFloat(defaultValue);
                }
                if (type.equals("double")) {
                    number = Double.parseDouble(defaultValue);
                }
                if (type.contains("String")) {
                    field.set(object, defaultValue);
                    return defaultValue;
                }
            } else {
                if (type.contains("String")) {
                    if (value.length() < min || value.length() > max) {
                        JOptionPane.showMessageDialog(null, "Value " + this.name + " need to be between : " + min + " and " + max, "Validator Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    field.set(object, value);
                    return value;
                }

                if (type.equals("boolean")) {
                    if (!value.isEmpty()) {
                        field.set(object, Boolean.parseBoolean(value));
                        return Boolean.parseBoolean(value);
                    } else {
                        JOptionPane.showMessageDialog(null, "value " + this.name + " can NOT be empty", "Validator Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (type.equals("byte")) number = Byte.parseByte(value);
                if (type.equals("short")) number = Short.parseShort(value);
                if (type.equals("int")) number = Integer.parseInt(value);
                if (type.equals("long")) number = Long.parseLong(value);
                if (type.equals("float")) number = Float.parseFloat(value);
                if (type.equals("double")) number = Double.parseDouble(value);
                if (number.intValue() > max || number.intValue() < min) {
                    JOptionPane.showMessageDialog(null, "Value " + this.name + " need to be between : " + min + " and " + max, "Validator Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }

            }
            field.set(object, number);
            return number;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Value " + this.name + " need to be " + type, "Validator Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }


}
