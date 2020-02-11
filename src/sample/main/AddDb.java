package sample.main;

import sample.dao.AnswerDao;
import sample.dao.QuestionDao;
import sample.model.Answer;
import sample.model.Question;

public class AddDb {
    public static void main(String[] args) {
        QuestionDao qd = new QuestionDao();
        AnswerDao ad = new AnswerDao();
        qd.createQuestion("Любимое пиво Володи Котлярова?");
        ad.createAnswer("Белый медведь", "Любимое пиво Володи Котлярова?", false);
        ad.createAnswer("Балтика 9", "Любимое пиво Володи Котлярова?", false);
        ad.createAnswer("Охота крепкая", "Любимое пиво Володи Котлярова?", true);
        ad.createAnswer("Essa", "Любимое пиво Володи Котлярова?", false);
        /*qd.createQuestion("Сколько веганов в группе Порнофильмы?");
       ad.createAnswer("1","Сколько веганов в группе Порнофильмы?",false);
        ad.createAnswer("2","Сколько веганов в группе Порнофильмы?",false);
        ad.createAnswer("3","Сколько веганов в группе Порнофильмы?",false);
        ad.createAnswer("Все","Сколько веганов в группе Порнофильмы?",true);


        qd.createQuestion("В каком году вышел первый альбом группы Нервы?");
        ad.createAnswer("2011","В каком году вышел первый альбом группы Нервы?", false);
        ad.createAnswer("2012", "В каком году вышел первый альбом группы Нервы?",true);
        ad.createAnswer("2013","В каком году вышел первый альбом группы Нервы?", false);
        ad.createAnswer("2014","В каком году вышел первый альбом группы Нервы?", false);
        qd.createQuestion("В каком году умер Фредди Меркьюри?");
        ad.createAnswer("1989","В каком году умер Фредди Меркьюри?",  false);
        ad.createAnswer("1990","В каком году умер Фредди Меркьюри?",  false);
        ad.createAnswer("1991", "В каком году умер Фредди Меркьюри?", true);
        ad.createAnswer("1992", "В каком году умер Фредди Меркьюри?", false);
        qd.createQuestion("Из какого города группа Порнофильмы?");
        ad.createAnswer("Дубна","Из какого города группа Порнофильмы?",  true);
        ad.createAnswer("Москва","Из какого города группа Порнофильмы?",  false);
        ad.createAnswer("Санкт-Петербург","Из какого города группа Порнофильмы?", false);
        ad.createAnswer("Тверь","Из какого города группа Порнофильмы?",  false);
        qd.createQuestion("В каком жанре исполнял песни Джонни Кэш?");
        ad.createAnswer("Панк-рок", "В каком жанре исполнял песни Джонни Кэш?", false);
        ad.createAnswer("Рок-н-ролл", "В каком жанре исполнял песни Джонни Кэш?", true);
        ad.createAnswer("Пост-панк", "В каком жанре исполнял песни Джонни Кэш?", false);
        ad.createAnswer("Электро-панк","В каком жанре исполнял песни Джонни Кэш?",  false);
        qd.createQuestion("Как зовут солиста группы Металлика?");
        ad.createAnswer("Джеймс Хэтфилд", "Как зовут солиста группы Металлика?", true);
        ad.createAnswer("Кирк Хэмметт","Как зовут солиста группы Металлика?",  false);
        ad.createAnswer("Адам Гоньте","Как зовут солиста группы Металлика?",  false);
        ad.createAnswer("Мэтт Уолст", "Как зовут солиста группы Металлика?", false);
        qd.createQuestion("Название последнего альбома группы Грин Дэй");
        ad.createAnswer("American Idiot","Название последнего альбома группы Грин Дэй", false);
        ad.createAnswer("Revolution Radio","Название последнего альбома группы Грин Дэй", true);
        ad.createAnswer("Father of All Motherfuckers","Название последнего альбома группы Грин Дэй", false);
        ad.createAnswer("21st Century Breakdown","Название последнего альбома группы Грин Дэй", false);

         */
    }
}
