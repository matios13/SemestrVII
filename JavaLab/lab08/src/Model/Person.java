package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pas113 on 2016-11-24.
 */

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    @XmlJavaTypeAdapter(value = StringPropertyAdapter.class, type = String.class)
    private StringProperty name;

    @XmlAttribute
    @XmlJavaTypeAdapter(value = IntegerPropertyAdapter.class, type = Integer.class)
    private IntegerProperty numbers;

    @XmlAttribute
    @XmlJavaTypeAdapter(value = IntegerPropertyAdapter.class, type = Integer.class)
    private IntegerProperty avgTime;

    @XmlAttribute
    @XmlJavaTypeAdapter(value = IntegerPropertyAdapter.class, type = Integer.class)
    private IntegerProperty shTime;

    @XmlAttribute
    @XmlJavaTypeAdapter(value = IntegerPropertyAdapter.class, type = Integer.class)
    private IntegerProperty lgTime;

    @XmlElements(@XmlElement(name="call"))
    private List<Call> calls;

    public Person() {
    }

    public Person(String name) {
        this.name=new SimpleStringProperty(name);
        this.numbers = new SimpleIntegerProperty(0);
        this.avgTime = new SimpleIntegerProperty(0);
        this.shTime = new SimpleIntegerProperty(0);
        this.lgTime = new SimpleIntegerProperty(0);
        calls = new ArrayList<>();
    }

//    public Person(StringProperty name, IntegerProperty numbers, IntegerProperty avgTime, IntegerProperty shTime, IntegerProperty lgTime) {
//        this.name = name;
//        this.numbers = numbers;
//        this.avgTime = avgTime;
//        this.shTime = shTime;
//        this.lgTime = lgTime;
//    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getNumbers() {
        return numbers.get();
    }

    public IntegerProperty numbersProperty() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers.set(numbers);
    }

    public int getAvgTime() {
        return avgTime.get();
    }

    public IntegerProperty avgTimeProperty() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime.set(avgTime);
    }

    public int getShTime() {
        return shTime.get();
    }

    public IntegerProperty shTimeProperty() {
        return shTime;
    }

    public void setShTime(int shTime) {
        this.shTime.set(shTime);
    }

    public int getLgTime() {
        return lgTime.get();
    }

    public IntegerProperty lgTimeProperty() {
        return lgTime;
    }

    public void setLgTime(int lgTime) {
        this.lgTime.set(lgTime);
    }

    public void addCall(Call call){
        List<Integer> l = new ArrayList<>();
        calls.add(call);
        numbers.set(calls.size());
        avgTime.set(calls.stream().collect(Collectors.averagingInt(c->c.getTime())).intValue());
        shTime.set(calls.stream().min(Call::compareTo).get().getTime());
        lgTime.set(calls.stream().max(Call::compareTo).get().getTime());

    }

    public int maxCmpareTo(Person o) {
        return Integer.compare(this.lgTime.get(),o.getLgTime());
    }
    public int minCmpareTo(Person o) {
        return Integer.compare(this.shTime.get(),o.getShTime());
    }
}
