package sample.controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentController  {
    private static AgentController instance;
    private String text;
    private String status;
    private String ip;

    public static AgentController getInstance() {
        if(instance == null) {
            instance = new AgentController();
        }
        return instance;
    }
    private AgentController() {
    }

    public String getStatus() {
        return status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
