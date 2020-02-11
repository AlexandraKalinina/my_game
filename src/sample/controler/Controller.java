package sample.controler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.dao.AnswerDao;
import sample.dao.QuestionDao;
import sample.model.Answer;
import sample.model.Question;

import java.io.IOException;
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
    @FXML
    public Button endButton;

    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<Answer> answers = new ArrayList<>();
    int index = 0;
    private int startTime = 11;
    private int startTimeSeconds = startTime;
    public int count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        QuestionDao qd = new QuestionDao();
        AnswerDao ad = new AnswerDao();
        count = 0;
        try {
             questions = qd.getListQuestion();
             answers = ad.createListAllAnswer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nextQuestion();
        doTime(11);    }
    private Button yourButton;
    private String yourAnswer = "";
    private boolean flag = false;
    public void answer(ActionEvent actionEvent) {
        yourButton = (Button) actionEvent.getSource();
        yourAnswer = yourButton.getText();
        Answer answer = getAnswerByButton(yourButton);
        flag = answer.isFlag();
        if (flag) {
            yourButton.getStyleClass().add("addColorGreen");
            count++;
        }
        else yourButton.getStyleClass().add("addColorRed");
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
    }
      public void nextQuestion() {
        if (index >= questions.size()) {
            button1.setDisable(true);
            button2.setDisable(true);
            button3.setDisable(true);
            button4.setDisable(true);
        }
        button1.setDisable(false);
        button2.setDisable(false);
        button3.setDisable(false);
        button4.setDisable(false);
        inputLabel.setText(questions.get(index).getText());
        for (int i=0; i < answers.size(); i=i+4) {
            if (answers.get(i).getId_question()==questions.get(index).getId()) {
                button1.setText(answers.get(i).getText());
                button2.setText(answers.get(i+1).getText());
                button3.setText(answers.get(i+2).getText());
                button4.setText(answers.get(i+3).getText());
            }
        }
        //doTime(11);
        index++;
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
                    if (yourAnswer.equals("")) {
                        empty();
                    }
                    else {
                        if (flag) {
                            yourButton.getStyleClass().remove("addColorGreen");
                        }
                        else yourButton.getStyleClass().remove("addColorRed");
                        sendToServer();
                    }
                    yourAnswer = "";
                    flag = false;
                    if (index >= questions.size()) {
                        button1.setDisable(true);
                        button2.setDisable(true);
                        button3.setDisable(true);
                        button4.setDisable(true);
                    } else {
                        nextQuestion();
                        doTime(11);
                    }
                }
            }
        });
        timeline.getKeyFrames().add(frame);
        timeline.playFromStart();

    }
    public void sendToServer() {

    }
    public void empty() {

    }
    public Answer getAnswerByButton(Button btn) {
        String text = btn.getText();
        AnswerDao ad = new AnswerDao();
        Answer answer = ad.getAnswerByText(text);
        return answer;
    }

    public void start(ActionEvent actionEvent) {
        endButton.setOnAction(event -> {
            endButton.getScene().getWindow().hide();
            String text = loadLabel();
            AgentController.getInstance().setText(text);
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
        });
    }

    public String loadLabel() {
        String s = "Вы набрали: " + count + "/" + questions.size() + " очков";
        return s;
    }
}
