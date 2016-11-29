package View;

import Database.Persons;
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

    //SUM table
    @FXML
    private TableView<Person> personTableSum;

    @FXML
    private TableColumn<Person, String> numberColumnSum;

    @FXML
    private TableColumn<Person, String> avgTimeColumnSum;

    @FXML
    private TableColumn<Person, String> lgTimeColumnSum;

    @FXML
    private TableColumn<Person, String> shTimeColumnSum;

    @FXML
    private TableColumn<Person, String> nameColumnSum;


    private Persons persons;

    public PersonController() {
    }

    @FXML
    private void initialize() {
        PersonController pc =this;
        actionColumn.setSortable(false);
        actionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        // create a cell value factory with an add button for each row in the table.
        actionColumn.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
            @Override
            public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> personBooleanTableColumn) {
                return new AddCallCell(personTable, persons,pc::recalculateSummary);
            }
        });
        addUserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userNameField.getText();
                if (!name.isEmpty()) {
                    persons.addPerson(new Person(name));
                    recalculateSummary();
                }
            }
        });
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        shTimeColumn.setCellValueFactory(cellData -> cellData.getValue().shTimeProperty().asString());
        lgTimeColumn.setCellValueFactory(c -> c.getValue().lgTimeProperty().asString());
        avgTimeColumn.setCellValueFactory(c -> c.getValue().avgTimeProperty().asString());
        numberColumn.setCellValueFactory(c -> c.getValue().numbersProperty().asString());

        personTableSum.setPlaceholder(new Label(""));
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
        personTable.setItems(persons.getPersonData());
    }

    public void recalculateSummary() {
        numberColumnSum.setText("" + persons.sumOfPersons());
        avgTimeColumnSum.setText("" + (int) persons.avgOfPersons());
        lgTimeColumnSum.setText("" + persons.maxOfPersons());
        shTimeColumnSum.setText("" + persons.minOfPersons());
    }
}
