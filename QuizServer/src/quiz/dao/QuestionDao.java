package quiz.dao;


import quiz.helpers.DbConnection;
import quiz.model.Question;

import java.sql.*;
import java.util.ArrayList;

public class QuestionDao implements Dao {
    private Connection conn;
    {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Question getQuestionById(int id) {
        String sql = "select * from question where id = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Question(rs.getInt("id"), rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void createQuestion(String text_question) {
        String sql = "insert into question values (nextval('question_id_seq'),?);";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, text_question);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Question getQuestionByText(String text) {
        String sql = "select * from question where text = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Question(rs.getInt("id"), rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Question> getListQuestion() throws SQLException {
        ArrayList<Question> questions = new ArrayList<>();
        String sql = "select * from question;";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Question question = new Question(rs.getInt("id"), rs.getString("text"));
            questions.add(question);
        }
        statement.close();
        return questions;
    }
}

