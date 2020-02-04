package sample.controler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
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
    @FXML
    public Label labelTime;

    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<Answer> answers = new ArrayList<>();
    int index = 0;
    boolean flag1, flag2, flag3, flag4;
    private int startTime = 11;
    private int startTimeSeconds = startTime;


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
        inputLabel.setText(questions.get(index).getText());
        labelTime.setTextFill(Color.BLACK);
        labelTime.setFont(Font.font(20));
        doTime(11);
        for (int i=0; i < answers.size(); i=i+4) {
            if (answers.get(i).getId_question()==questions.get(index).getId()) {
                button1.setText(answers.get(i).getText());
                button2.setText(answers.get(i+1).getText());
                button3.setText(answers.get(i+2).getText());
                button4.setText(answers.get(i+3).getText());
            }
        }
        index++;
    }

    public void answer() {
        doTime(11);
        inputLabel.setText(questions.get(index).getText());
        for (int i=0; i < answers.size(); i=i+4) {
            if (answers.get(i).getId_question()==questions.get(index).getId()) {
                button1.setText(answers.get(i).getText());
                button2.setText(answers.get(i+1).getText());
                button3.setText(answers.get(i+2).getText());
                button4.setText(answers.get(i+3).getText());
                flag1 = answers.get(i).isFlag();
                flag2 = answers.get(i+1).isFlag();
                flag3 = answers.get(i+2).isFlag();
                flag4 = answers.get(i+3).isFlag();
            }
            color(button1, flag1);
            color(button2, flag2);
            color(button3, flag3);
            color(button4, flag4);

        }
        index++;
    }
    public void color(Button btn, Boolean flag) {
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (flag) {
                    btn.setStyle("-fx-background-color: green");
                }
                else btn.setStyle("-fx-background-color: red");
            }
        });
    }
    private void doTime(int time) {
        startTimeSeconds = time;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        if (timeline!=null) {
            timeline.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimeSeconds--;
                labelTime.setText("У вас осталось: "+ startTimeSeconds + " секунд");
                if (startTimeSeconds<=0) {
                    timeline.stop();
                    answer();
                }
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.playFromStart();
    }
}
