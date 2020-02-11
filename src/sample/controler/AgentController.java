package sample.controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentController  {
    private static AgentController instance;
    private String text;

    public static AgentController getInstance() {
        if(instance == null) {
            instance = new AgentController();
        }
        return instance;
    }
    private AgentController() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
