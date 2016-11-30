package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Uzytkownik on 30.11.2016.
 */
public class IntegerPropertyAdapter extends XmlAdapter<Integer,IntegerProperty> {
    @Override
    public IntegerProperty unmarshal(Integer v) throws Exception {
        return new SimpleIntegerProperty(v);
    }

    @Override
    public Integer marshal(IntegerProperty v) throws Exception {
        if(null == v) {
            return null;
        }
        return v.get(); // Or whatever the correct method is
    }
}
