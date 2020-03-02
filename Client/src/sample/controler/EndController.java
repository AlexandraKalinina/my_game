package sample.controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {
    @FXML
    public Label count;
    public Label statusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String text = AgentController.getInstance().getText();
        String yourStatus = AgentController.getInstance().getStatus();
        count.setText(text);
        statusLabel.setText(yourStatus);
    }
}
