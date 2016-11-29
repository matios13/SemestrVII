package Database;

import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

/**
 * Created by pas113 on 2016-11-24.
 */
public class Persons {
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    public Persons(){
        personData.addAll(Arrays.asList(new Person("Test"),new Person("Test2")));
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public void addPerson(Person person){
        personData.add(person);
    }
}
