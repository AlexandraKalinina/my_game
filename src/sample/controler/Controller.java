package sample.controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.dao.QuestionDao;
import sample.model.Question;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button button3;
    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button4;
    @FXML
    public Label inputLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        QuestionDao qd = new QuestionDao();
        try {
            ArrayList<Question> questions = qd.getListQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //inputLabel.setText();
    }
}
