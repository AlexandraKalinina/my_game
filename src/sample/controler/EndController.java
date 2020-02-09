package sample.controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {
    @FXML
    public javafx.scene.control.Label countLabel;
    @FXML
    public Controller mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/fxml/end.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        countLabel.setText(mainController.loadLabel());
    }
    /*public String addLabel(int count, int countQuestion) {
        String s = "Вы набрали: " + count + "/" + countQuestion + " очков";
        return s;
    }

     */

    public void init(Controller controller) {
        mainController = controller;
    }
}
