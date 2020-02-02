package sample.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.dao.AnswerDao;
import sample.dao.QuestionDao;
import sample.model.Answer;
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
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<Answer> answers = new ArrayList<>();
    int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        QuestionDao qd = new QuestionDao();
        AnswerDao ad = new AnswerDao();
        try {
             questions = qd.getListQuestion();
             answers = ad.createListAllAnswer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inputLabel.setText(questions.get(index++).getText());
        /*for (int i=0; i < answers.size()-4; i=i+4) {
            if (answers.get(i).getId_question()==questions.get(index++).getId()) {
                button1.setText(answers.get(i).getText());
                button2.setText(answers.get(i+1).getText());
                button3.setText(answers.get(i+2).getText());
                button4.setText(answers.get(i+3).getText());
            }

        }

         */

    }

    public void answer(ActionEvent actionEvent) {
        inputLabel.setText(questions.get(index++).getText());
        /*for (int i=0; i < answers.size()-4; i=i+4) {
            if (answers.get(i).getId_question()==questions.get(index++).getId()) {
                button1.setText(answers.get(i).getText());
                button2.setText(answers.get(i+1).getText());
                button3.setText(answers.get(i+2).getText());
                button4.setText(answers.get(i+3).getText());
            }

        }

         */

    }
}
