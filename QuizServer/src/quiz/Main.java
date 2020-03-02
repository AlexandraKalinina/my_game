package quiz;

import quiz.dao.AnswerDao;
import quiz.dao.QuestionDao;
import quiz.model.Answer;

public class Main {

    public static void main(String[] args) {
	// write your code here
        AnswerDao ad = new AnswerDao();
        QuestionDao qd = new QuestionDao();
        qd.createQuestion("Как Горшок зубы потерял?");
        ad.createAnswer("В драке", "Как Горшок зубы потерял?", false);
        ad.createAnswer("Повис на турнике", "Как Горшок зубы потерял?", true);
        ad.createAnswer("Удалил специально","Как Горшок зубы потерял?", false);
        ad.createAnswer("Наркотики", "Как Горшок зубы потерял?", false);
        qd.createQuestion("Где зародился панк-рок?");
        ad.createAnswer("Англия", "Где зародился панк-рок?", false);
        ad.createAnswer("Россия", "Где зародился панк-рок?", false);
        ad.createAnswer("Португалия","Где зародился панк-рок?", false);
        ad.createAnswer("США", "Где зародился панк-рок?", true);
        qd.createQuestion("Любимое пиво Володи Котлярова?");
        ad.createAnswer("Белый медведь", "Любимое пиво Володи Котлярова?", false);
        ad.createAnswer("Балтика 9", "Любимое пиво Володи Котлярова?", false);
        ad.createAnswer("Охота Крепкая","Любимое пиво Володи Котлярова?", true);
        ad.createAnswer("Essa", "Любимое пиво Володи Котлярова?", false);
    }
}
