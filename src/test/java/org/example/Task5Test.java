package org.example;

import org.example.data.Attempt;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Класс тестов для задачи 5
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Task5Test {
    /** Составитель статистики */
    private final IUserStatistic statistic = new UserStatistic();

    /**
     * Функция инициализации аналога поля {@link User#userResults}
     */
    public Map<String, List<Attempt>> initMap(){
        Map<String, List<Attempt>> statistic = new LinkedHashMap<>();
        for (Subjects subject : Subjects.values()){
            statistic.put(subject.toString(), new LinkedList<>());
        }
        return statistic;
    }
    /**
     * Тест на корректную обработку статистики по одному предмету
     */
    @Test
    public void testSubjectStatistic(){
        List<Attempt> maths = new LinkedList<>();
        Assert.assertEquals("MATHS: нет информации, пройдите тест.\n",
                statistic.getSubjectStat(maths, "MATHS"));
        Testing test = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        Attempt m1 = new Attempt("1", test.getAnswers());
        for (int i = 0; i<5; i++) {
            test.newLine();
            test.isAnswerRight(i % 2 == 0);
        }
        maths.add(m1);
        Assert.assertEquals("MATHS: попытка №1: 3 - правильных, 2 - неправильных\n",
                statistic.getSubjectStat(maths, "MATHS"));

        Testing test2 = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        test2.newLine();
        test2.isAnswerRight(true);
        Attempt m2 = new Attempt("2", test2.getAnswers());
        maths.add(m2);
        Assert.assertEquals("MATHS: попытка №1: 3 - правильных, 2 - неправильных\n" +
                "MATHS: попытка №2: 1 - правильных, 0 - неправильных\n",
                statistic.getSubjectStat(maths, "MATHS"));
    }

    /**
     * Проверка начала формирования статистики при запуске теста
     * (запуск теста фиксируется, даже если пользователь не начал отвечать на вопросы)
     */
    @Test
    public void unfinishedTest(){
        Map<String, List<Attempt>> information = initMap();
        Testing test = new Testing(true, new HashMap<>(), Subjects.RUSSIAN.value());
        Attempt r1 = new Attempt("1", test.getAnswers());
        information.get("RUSSIAN").add(r1);
        Assert.assertEquals("RUSSIAN: попытка №1: Вы не дали ни одного ответа\n",
                statistic.getSubjectStat(information.get("RUSSIAN"), "RUSSIAN"));
        Assert.assertEquals("MATHS: нет информации, пройдите тест.\n" +
                        "ENGLISH: нет информации, пройдите тест.\n" +
                        "RUSSIAN: Вы не дали ни одного ответа\n",
                statistic.getLastAttemptSubjectStat(information));
    }

    /**
     * Тест на корректную обработку общей статистики
     */
    @Test
    public void testGeneralStatistic(){
        Map<String, List<Attempt>> information = initMap();
        Testing testMath = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        testMath.newLine();
        testMath.isAnswerRight(false);
        information.get("MATHS").add(new Attempt("1", testMath.getAnswers()));
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                        "ENGLISH: нет информации, пройдите тест.\n" +
                        "RUSSIAN: нет информации, пройдите тест.\n",
                statistic.getLastAttemptSubjectStat(information));

        Testing testEng = new Testing(true, new HashMap<>(), Subjects.ENGLISH.value());
        for (int i = 0; i<3; i++){
            testEng.newLine();
            testEng.isAnswerRight(i%2 == 0);
        }
        information.get("ENGLISH").add(new Attempt("1", testEng.getAnswers()));
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                                    "ENGLISH: 2 - правильных, 1 - неправильных\n" +
                                    "RUSSIAN: нет информации, пройдите тест.\n" ,
                statistic.getLastAttemptSubjectStat(information));
        Testing testMath2 = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        testMath2.newLine(); testMath2.isAnswerRight(true);
        testMath2.newLine(); testMath2.isAnswerRight(false);
        information.get("MATHS").add(new Attempt("2", testMath2.getAnswers()));
        Assert.assertEquals("MATHS: 1 - правильных, 1 - неправильных\n" + //!!!!
                                    "ENGLISH: 2 - правильных, 1 - неправильных\n" +
                                    "RUSSIAN: нет информации, пройдите тест.\n"  ,
                statistic.getLastAttemptSubjectStat(information));

    }
}
