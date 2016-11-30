package Database;

import Model.Alerts;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by pas113 on 2016-11-24.
 */
@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {

    @XmlElement(name = "person")
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

    public void createXml(String name){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //WYŚWIETLENIE NA OUT
            if(!name.contains(".xml"))
                name+=".xml";
            File file = new File(name);
            jaxbMarshaller.marshal(this, file);
            Alerts.showAddedXmlDialog(name,file.getAbsolutePath());
        } catch (JAXBException e) {
            Alerts.showErrorAddedXmlDialog(name,e.getErrorCode());
            e.printStackTrace();
        }

    }

    public void loadXml(String name){
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            if(!name.contains(".xml"))
                name+=".xml";
            File file = new File(name);
            Persons personsFromXml = (Persons) jaxbUnmarshaller.unmarshal(file);
            personsFromXml.personData.stream().forEach(p->personData.add(p));
            Alerts.showLoadedXmlDialog(name,file.getAbsolutePath());
        } catch (JAXBException e) {
            Alerts.showErrorLoadingXmlDialog(name,e.getErrorCode());
            e.printStackTrace();
        }

    }

}
