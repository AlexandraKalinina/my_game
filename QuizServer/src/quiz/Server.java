package quiz;

import quiz.dao.AnswerDao;
import quiz.dao.QuestionDao;
import quiz.model.Answer;
import quiz.model.Question;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server implements Listener {
    private final int PORT = 1234;
    private List<ClientConnection> connections = new ArrayList<>();
    private List<Question> questions;
    private Map<String, Integer> players;
    private String message;
    private int index = 0;
    private QuestionDao qd;
    private AnswerDao ad;
    private List<Answer> answerQuestion;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        System.out.println("Run");
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                new ClientConnection(this, ss.accept());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public synchronized void connect(ClientConnection connection) {
        connections.add(connection);
        players = new HashMap<>();
        if (connections.size() == 1) {
            players.put(connection.getName(), 0);
            getQuestion();
        }
        checkPlayer(connection);
    }
    private void checkPlayer(ClientConnection connect) {
        if (!players.containsKey(connect.getName()) ) {
            players.put(connect.getName(), 0);
        }
        return;
    }
    @Override
    public synchronized void disconnect(ClientConnection connection) {
        connections.remove(connection);
        players.remove(connection.getName());
        sendPlayer();
        System.out.println(connection.getName() + " disconnected");
    }

    @Override
    public synchronized void onReceive(ClientConnection connection, String answer) {
        checkPlayer(connection);
        if (answer.startsWith("question")) {
            listQuestion();
        } else if (answer.startsWith("answer")) {
            String[] data = answer.split("\\.");
            check(data[1], connection);
        }
    }
    private void check(String msg, ClientConnection connect) {
        ad = new AnswerDao();
        if (ad.getAnswerByText(msg).isFlag()) {
            connect.sendAnswer("flag" + "." + "true" + ".");
            players.put(connect.getName(), players.get(connect.getName()) + 1);
        }
        else connect.sendAnswer("flag" + "." + "false" + ".");
    }

    private void getQuestion() {
        message = "question.";
        qd = new QuestionDao();
        try {
            questions = qd.getListQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ad = new AnswerDao();
        message += questions.get(index).getText() + ".";
        answerQuestion = ad.createListAnswer(questions.get(index).getId());
        message += answerQuestion.get(0).getText() + "." + answerQuestion.get(1).getText() + "."
                + answerQuestion.get(2).getText() + "." + answerQuestion.get(3).getText() + ".";
        for (ClientConnection c : connections) {
            c.sendAnswer(message);
        }
    }
    public void listQuestion() {
        if (index == questions.size() - 1) {
            String win = "";
            int countOfWin = 0;
            for (String k : players.keySet()) {
                if (players.get(k) > countOfWin) {
                    countOfWin = players.get(k);
                    win = k;
                }
            }
            if (!win.equals("")) {
                for (ClientConnection c : connections) {
                    if (c.getName().equals(win)) {
                        c.sendAnswer("win" + "." + countOfWin + ".");
                    }
                    else c.sendAnswer("lox" + ".");
                }
            }
            else {
                for (ClientConnection c : connections) {
                    c.sendAnswer("draw" + ".");
                }
            }
            sendPlayer();
        }
        else {
            index++;
            getQuestion();
        }
    }
    private void sendPlayer() {
        String msg = "player" + ".";
        for (String key : players.keySet()) {
            msg += key + "." + players.get(key) + ".";
        }
        msg +=  questions.size() + ".";
        for (ClientConnection c : connections) {
            c.sendAnswer(msg);
        }
    }
}
