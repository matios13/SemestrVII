package Database;

import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by pas113 on 2016-11-24.
 */
public class Persons {
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    public Persons(){}

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public void addPerson(Person person){
        personData.add(person);
    }

    public int sumOfPersons(){
        return personData.stream().collect(Collectors.summingInt(p->p.getNumbers()));
    }
    public double avgOfPersons(){
        return personData.stream().collect(Collectors.averagingInt(p->p.getAvgTime()));
    }
    public int maxOfPersons(){
        return personData.stream().max(Person::maxCmpareTo).get().getLgTime();
    }
    public int minOfPersons(){
        try {
            return personData.stream().filter(p -> p.getNumbers() > 0).min(Person::minCmpareTo).get().getShTime();
        }catch (NullPointerException e){
            return 0;
        }catch (NoSuchElementException e){
            return 0;
        }
    }
}
