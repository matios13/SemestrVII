package View;

import Database.Persons;
import Model.Call;
import Model.Person;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

/**
 * Created by pas113 on 2016-11-24.
 */
public class PersonController {

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> numberColumn;

    @FXML
    private TableColumn<Person, String> avgTimeColumn;

    @FXML
    private TableColumn<Person, String> lgTimeColumn;

    @FXML
    private TableColumn<Person, String> shTimeColumn;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, Boolean> actionColumn;

    @FXML
    private Button addUserBtn;

    @FXML
    private TextField userNameField;


    private Persons persons;

    public PersonController() {
    }

    @FXML
    private void initialize() {
        actionColumn.setSortable(false);
        actionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, Boolean>, ObservableValue<Boolean>>() {
            @Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        // create a cell value factory with an add button for each row in the table.
        actionColumn.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
            @Override public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> personBooleanTableColumn) {
                return new AddCallCell(personTable,persons);
            }
        });
        addUserBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent actionEvent) {
                String name = userNameField.getText();
                if(!name.isEmpty()){
                    persons.addPerson(new Person(name));
                }
            }
        });
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        shTimeColumn.setCellValueFactory(cellData-> cellData.getValue().shTimeProperty().asString());
        lgTimeColumn.setCellValueFactory(c->c.getValue().lgTimeProperty().asString());
        avgTimeColumn.setCellValueFactory(c->c.getValue().avgTimeProperty().asString());
        numberColumn.setCellValueFactory(c->c.getValue().numbersProperty().asString());
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
        personTable.setItems(persons.getPersonData());
    }
}
