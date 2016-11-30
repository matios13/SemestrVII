package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pas113 on 2016-11-24.
 */
@XmlRootElement(name="call")
@XmlAccessorType(XmlAccessType.FIELD)
public class Call implements Comparable<Call>{
    @XmlAttribute
    private int time;

    public Call() {
    }

    public Call(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Call o) {
        return Integer.compare(this.time,o.getTime());
    }


}
