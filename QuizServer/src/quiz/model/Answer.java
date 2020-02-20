package quiz.model;


public class Answer {
    private int id;
    private String text;
    private int id_question;
    private boolean flag;

    public Answer(int id, String text, int id_question, boolean flag) {
        this.id = id;
        this.text = text;
        this.id_question = id_question;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", id_question=" + id_question +
                ", flag=" + flag +
                '}';
    }
}

