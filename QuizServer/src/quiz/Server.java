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
    private final int PORT = 8080;
    private List<ClientConnection> connections = new ArrayList<>();
    private List<Question> questions;
    private Map<String, Integer> players;
    private StringBuffer message;
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
        String[] data = answer.split("\\.");
        if (answer.startsWith("question")) {
            //System.out.println(data[1]);
            listQuestion(data[1]);
        } else if (answer.startsWith("answer")) {
            update(data[1], connection);
        }
    }
    public void update(String ans, ClientConnection connect) {
        ad = new AnswerDao();
        if (ad.getAnswerByText(ans).isFlag()) {
            players.put(connect.getName(), players.get(connect.getName()) + 1);
            System.out.println(connect.getName() + " " + players.get(connect.getName()));
        }
    }
    private void getQuestion() {
        message = new StringBuffer("question" + ".");
        qd = new QuestionDao();
        try {
            questions = qd.getListQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ad = new AnswerDao();
        message.append(questions.get(index).getText()).append(".");
        answerQuestion = ad.createListAnswer(questions.get(index).getId());
        message.append(answerQuestion.get(0).getText()).append(".")
                .append(answerQuestion.get(1).getText()).append(".")
                .append(answerQuestion.get(2).getText()).append(".")
                .append(answerQuestion.get(3).getText()).append( ".");
        if (answerQuestion.get(0).isFlag()) {
            message.append(answerQuestion.get(0).getText()).append(".");
        } else if (answerQuestion.get(1).isFlag()) {
            message.append(answerQuestion.get(1).getText()).append(".");
        } else if (answerQuestion.get(2).isFlag()) {
            message.append(answerQuestion.get(2).getText()).append(".");
        } else message.append(answerQuestion.get(3).getText()).append(".");
        message.append(index).append(".");
        for (ClientConnection c : connections) {
            c.sendAnswer(message);
        }
    }
    public void listQuestion(String data) {
        if (index == Integer.parseInt(data)) {
            if (index == questions.size() - 1) {
                System.out.println(index);
                System.out.println(questions.size());
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
                            StringBuffer sb = new StringBuffer();
                            sb.append("win").append(".").append(countOfWin).append(".");
                            System.out.println("win" + " " + c.getName());
                            c.sendAnswer(sb);

                        } else {
                            StringBuffer sb2 = new StringBuffer();
                            sb2.append("lox").append(".");
                            System.out.println("lox" + " " + c.getName());
                            c.sendAnswer(sb2);
                        }
                    }
                } else {
                    StringBuffer sb1 = new StringBuffer("draw");
                    for (ClientConnection c : connections) {
                        c.sendAnswer(sb1.append("."));
                    }
                }
                sendPlayer();
                index++;
            } else {
                index++;
                getQuestion();
            }
        }
    }
    private void sendPlayer() {
        StringBuffer msg = new StringBuffer("player");
        msg.append(".");
        for (String key : players.keySet()) {
            msg.append(key).append(".").append(players.get(key)).append(".");
        }
        msg.append(questions.size()).append(".");
        for (ClientConnection c : connections) {
            c.sendAnswer(msg);
        }
    }
}
