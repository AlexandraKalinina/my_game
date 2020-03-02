package test;

import org.junit.Assert;
import org.junit.Test;
import quiz.dao.QuestionDao;

import java.sql.SQLException;

public class QuestionDaoTest {
    private QuestionDao qd;
    @Test
    public void getListQuestion() throws SQLException {
        qd = new QuestionDao();
        Assert.assertNotNull(qd.getListQuestion());
    }
    @Test
    public void getQuestionByText() {
        qd = new QuestionDao();
        Assert.assertEquals(6, qd.getQuestionByText("В каком году умер Фредди Меркьюри?").getId());
    }
}
