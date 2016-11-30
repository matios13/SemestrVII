package Model;

import javafx.scene.control.Alert;

/**
 * Created by Uzytkownik on 30.11.2016.
 */
public class Alerts {
    public static void showAddedXmlDialog(String name,String path){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Created XML");
        alert.setHeaderText("Created XML with name : "+name);
        alert.setContentText("Created XML at : \n"+path);

        alert.showAndWait();
    }

    public static void showErrorAddedXmlDialog(String name,String exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR XML");
        alert.setHeaderText("Created XML with name : "+name + "gone wrong");
        alert.setContentText("Exception : "+exception);

        alert.showAndWait();
    }
    public static void showErrorLoadingXmlDialog(String name,String exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR XML");
        alert.setHeaderText("Reading XML with name : "+name + "gone wrong");
        alert.setContentText("Exception : "+exception);

        alert.showAndWait();
    }
    public static void showErrorCantFindLoadingXmlDialog(String name,String path){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR XML");
        alert.setHeaderText("Reading XML with name : "+name + "gone wrong");
        alert.setContentText("Cant Find : "+path);

        alert.showAndWait();
    }
    public static void showLoadedXmlDialog(String name,String path){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reading Succesfull XML");
        alert.setHeaderText("Reading XML with name : "+name+"was succesfull");
        alert.setContentText("Reading XML from : \n"+path);

        alert.showAndWait();
    }
}
