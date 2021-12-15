package org.example;

import org.junit.Assert;
import org.junit.Test;


/**
 * Тесты на класс {@link UserStatistic}
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class UserStatisticTest {
    /**
     * Тест на корректную обработку общей статистики
     */
    @Test
    public void testGeneralStatistic()
    {
        UserStatistic statistic = new UserStatistic();
        statistic.startGenerateStat("MATHS");
        statistic.createLastTestResult(0, 1, "MATHS");
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                        "RUSSIAN: нет информации, пройдите тест.\n" +
                        "ENGLISH: нет информации, пройдите тест.\n",
                statistic.makeStatGeneral());

        statistic.startGenerateStat("ENGLISH");
        statistic.createLastTestResult(2, 1, "ENGLISH");
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                        "RUSSIAN: нет информации, пройдите тест.\n" +
                        "ENGLISH: 2 - правильных, 1 - неправильных\n",
                statistic.makeStatGeneral());

        statistic.startGenerateStat("MATHS");
        statistic.createLastTestResult(1, 0, "MATHS");
        Assert.assertEquals("MATHS: 1 - правильных, 0 - неправильных\n" +
                        "RUSSIAN: нет информации, пройдите тест.\n" +
                        "ENGLISH: 2 - правильных, 1 - неправильных\n",
                statistic.makeStatGeneral());
    }

    /**
     * Тест на корректную обработку предметной статистики
     */
    @Test
    public void testSubjectStatistic()
    {
        UserStatistic statistic = new UserStatistic();

        Assert.assertEquals("MATHS: Информации нет. Пройдите тест.",
                statistic.makeStatSubject("MATHS"));

        statistic.startGenerateStat("MATHS");
        statistic.createLastTestResult(0, 1, "MATHS");
        Assert.assertEquals("MATHS: попытка №1: 0 - правильных, 1 - неправильных\n",
                statistic.makeStatSubject("MATHS"));

        statistic.startGenerateStat("MATHS");
        statistic.createLastTestResult(1, 0, "MATHS");
        Assert.assertEquals("MATHS: попытка №1: 0 - правильных, 1 - неправильных\n" +
                        "MATHS: попытка №2: 1 - правильных, 0 - неправильных\n",
                statistic.makeStatSubject("MATHS"));

        statistic.startGenerateStat("RUSSIAN");
        statistic.createLastTestResult(0, 1, "RUSSIAN");
        Assert.assertEquals("RUSSIAN: попытка №1: 0 - правильных, 1 - неправильных\n",
                statistic.makeStatSubject("RUSSIAN"));
    }

    /**
     * Проверка начала формирования статистики при запуске теста
     * (запуск теста фиксируется, даже если пользователь не начал отвечать на вопросы)
     */
    @Test
    public void unfinishedTest(){
        UserStatistic statistic = new UserStatistic();
        statistic.startGenerateStat("MATHS");
        Assert.assertEquals("MATHS: попытка №1: Вы не дали ни одного ответа\n",
                statistic.makeStatSubject("MATHS"));
    }
}
