package org.example;

import org.example.data.Attempt;
import org.example.data.SubjectResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
    public List<SubjectResult> initMap(){
        List<SubjectResult> statistic = new LinkedList<>();
        for (Subjects subject : Subjects.values()){
            SubjectResult result = new SubjectResult(subject.toString(), new LinkedList<>());
            statistic.add(result);
        }
        return statistic;
    }
    /**
     * Тест на корректную обработку статистики по одному предмету
     */
    @Test
    public void testSubjectStatistic(){
        SubjectResult maths = new SubjectResult("MATHS", null);
        Assert.assertEquals("MATHS: Информации нет. Пройдите тест.", statistic.getSubjectStat(maths));

        Testing test = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        for (int i = 0; i<5; i++) {
            test.newLine();
            test.isAnswerRight(i % 2 == 0); // такой метод должен появиться
        }
        Attempt m1 = new Attempt("1", test.getAnswers()); // и такой метод должен появиться
        maths.add(m1);
        Assert.assertEquals("MATHS: попытка №1: 3 - правильных, 2 - неправильных\n",
                statistic.getSubjectStat(maths));

        Testing test2 = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        test.newLine();
        test.isAnswerRight(true);
        Attempt m2 = new Attempt("2", test2.getAnswers());
        maths.add(m2);
        Assert.assertEquals("MATHS: попытка №1: 3 - правильных, 2 - неправильных\n" +
                "MATHS: попытка №2: 1 - правильных, 0 - неправильных\n",
                statistic.getSubjectStat(maths));
    }

    /**
     * Проверка начала формирования статистики при запуске теста
     * (запуск теста фиксируется, даже если пользователь не начал отвечать на вопросы)
     */
    @Test
    public void unfinishedTest(){
        List<SubjectResult> information = initMap();
        Testing test = new Testing(true, new HashMap<>(), Subjects.RUSSIAN.value());
        Attempt r1 = new Attempt("1", test.getAnswers());
        SubjectResult rus = new SubjectResult("RUSSIAN", List.of(r1));
        Assert.assertEquals("RUSSIAN: попытка №1: Вы не дали ни одного ответа\n",
                statistic.getSubjectStat(rus));

        SubjectResult maths = new SubjectResult("MATHS", null);
        SubjectResult eng = new SubjectResult("ENGLISH", null);
        information.addAll(List.of(maths, rus, eng));
        Assert.assertEquals("MATHS: нет информации, пройдите тест.\n" +
                        "RUSSIAN: Вы не дали ни одного ответа\n" +
                        "ENGLISH: нет информации, пройдите тест.\n",
                statistic.getLastAttemptSubjectStat(information));
    }

    /**
     * Тест на корректную обработку общей статистики
     */
    @Test
    public void testGeneralStatistic(){
        List<SubjectResult> information = initMap();
        Testing testMath = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        testMath.newLine(); testMath.isAnswerRight(false);
        SubjectResult maths = new SubjectResult("MATHS",
                List.of(new Attempt("1", testMath.getAnswers())));
        SubjectResult rus = new SubjectResult("RUSSIAN", null);
        SubjectResult eng = new SubjectResult("ENGLISH", null);
        information.addAll(List.of(maths, rus, eng));
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                "RUSSIAN: нет информации, пройдите тест.\n" +
                "ENGLISH: нет информации, пройдите тест.\n",
                statistic.getLastAttemptSubjectStat(information));

        Testing testEng = new Testing(true, new HashMap<>(), Subjects.ENGLISH.value());
        for (int i = 0; i<3; i++){
            testEng.newLine();
            testEng.isAnswerRight(i%2 == 0);
        }
        eng.add(new Attempt("1", testEng.getAnswers()));
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                "RUSSIAN: нет информации, пройдите тест.\n" +
                "ENGLISH: 2 - правильных, 1 - неправильных\n",
                statistic.getLastAttemptSubjectStat(information));
        Testing testMath2 = new Testing(true, new HashMap<>(), Subjects.MATHS.value());
        testMath2.newLine(); testMath2.isAnswerRight(true);
        testMath2.newLine(); testMath2.isAnswerRight(false);
        maths.add(new Attempt("2", testMath2.getAnswers()));
        Assert.assertEquals("MATHS: 1 - правильных, 1 - неправильных\n" + //!!!!
                "RUSSIAN: нет информации, пройдите тест.\n" +
                "ENGLISH: 2 - правильных, 1 - неправильных\n",
                statistic.getLastAttemptSubjectStat(information));

    }
}
