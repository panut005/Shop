package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
    @FXML
    private Button buttonCashier,buttonManager;

    @FXML
    public void handlebuttonCashier(ActionEvent event) throws IOException {
        buttonCashier= (Button) event.getSource();
        Stage stage = (Stage) buttonCashier.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }

    @FXML
    public void handlebuttonManager(ActionEvent event) throws IOException {
        buttonManager= (Button) event.getSource();
        Stage stage = (Stage) buttonManager.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemlist.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        stage.show();

    }
}
