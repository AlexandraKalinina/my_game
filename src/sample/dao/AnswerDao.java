package sample.dao;

import sample.helpers.DbConnection;
import sample.model.Answer;
import sample.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDao implements DAO {
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
}
