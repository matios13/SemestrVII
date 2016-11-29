package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pas113 on 2016-11-24.
 */
public class Person {
    private StringProperty name;
    private IntegerProperty numbers;
    private IntegerProperty avgTime;
    private IntegerProperty shTime;
    private IntegerProperty lgTime;
    private List<Call> calls;

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
}
