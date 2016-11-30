package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Uzytkownik on 30.11.2016.
 */
public class StringPropertyAdapter extends XmlAdapter<String,StringProperty> {
    @Override
    public StringProperty unmarshal(String v) throws Exception {
        return new SimpleStringProperty(v);
    }

    @Override
    public String marshal(StringProperty v) throws Exception {
        if(null == v) {
            return null;
        }
        return v.get(); // Or whatever the correct method is
    }
}
