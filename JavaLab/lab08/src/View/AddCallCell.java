package View;

import Database.Persons;
import Model.Call;
import Model.Person;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.function.Function;

/**
 * Created by Uzytkownik on 29.11.2016.
 */
class AddCallCell extends TableCell<Person, Boolean> {
    // a button for adding a new person.
    private final Button addButton       = new Button("Add");
    // pads and centers the add button in the cell.
    private final StackPane paddedButton = new StackPane();

    private Persons persons;

    /**
     * AddPersonCell constructor
     * @param table the table to which a new person can be added.
     */
    AddCallCell(final TableView table, Persons persons ,Runnable summaryRefresh) {
        this.persons = persons;
        paddedButton.setPadding(new Insets(3));
        paddedButton.getChildren().add(addButton);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                addCallPopup(getTableRow().getIndex(),summaryRefresh);
            }
        });
    }

    private void addCallPopup(int i, Runnable summaryRefresh){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        final Button addButton = new Button("Add Call");
        TextField nameField = new TextField("0");
        dialogVbox.getChildren().add(new Text("Add new call to "+persons.getPersonData().get(i).getName()));
        dialogVbox.getChildren().add(nameField);
        dialogVbox.getChildren().add(addButton);
        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    nameField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent actionEvent) {
                persons.getPersonData().get(i).addCall(new Call(new Integer(nameField.getText())));
                dialog.close();
                summaryRefresh.run();
            }
        });
        Scene dialogScene = new Scene(dialogVbox, 200, 150);
        dialog.setScene(dialogScene);
        dialog.show();

    }
    /** places an add button in the row only if the row is not empty. */
    @Override protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(paddedButton);
        } else {
            setGraphic(null);
        }
    }


}
