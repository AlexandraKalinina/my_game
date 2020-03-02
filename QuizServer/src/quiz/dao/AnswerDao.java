package quiz.dao;


import quiz.helpers.DbConnection;
import quiz.model.Answer;
import quiz.model.Question;

import java.sql.*;
import java.util.ArrayList;

public class AnswerDao implements Dao {
    private Connection conn;
    {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void createAnswer(String text, String text_question, boolean flag) {
        QuestionDao qd = new QuestionDao();
        Question q = qd.getQuestionByText(text_question);
        String sql = "insert into answer values (nextval('answer_id_seq'),?,?,?);";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, text);
            ps.setInt(2, q.getId());
            ps.setBoolean(3, flag);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Answer getAnswerByText(String text) {
        String sql = "select * from answer where text = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Answer(rs.getInt("id"), rs.getString("text"),
                        rs.getInt("id_question"), rs.getBoolean("flag"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Answer> createListAnswer(int id_question) {
        String sql = "select * from answer where id_question = ?;";
        ArrayList<Answer> answers = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_question);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                answers.add(new Answer(rs.getInt("id"), rs.getString("text"),
                        rs.getInt("id_question"), rs.getBoolean("flag")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
    public ArrayList<Answer> createListAllAnswer() throws SQLException {
        ArrayList<Answer> answers = new ArrayList<>();
        String sql = "select * from answer;";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            answers.add(new Answer(rs.getInt("id"), rs.getString("text"),
                    rs.getInt("id_question"), rs.getBoolean("flag")));
        }
        return answers;
    }
}

