package test;

import org.junit.Assert;
import org.junit.Test;
import quiz.dao.AnswerDao;
import quiz.model.Answer;

import java.sql.SQLException;
import java.util.List;

public class AnswerDaoTest {
    private AnswerDao ad;
    @Test
    public void getAnswerByText() {
        ad = new AnswerDao();
        Assert.assertNotNull(ad.getAnswerByText("2011"));
    }
    @Test
    public void createListAllAnswer() throws SQLException {
        ad = new AnswerDao();
        List<Answer> answers = ad.createListAllAnswer();
        Assert.assertEquals("1",answers.get(0).getText());
    }
}
