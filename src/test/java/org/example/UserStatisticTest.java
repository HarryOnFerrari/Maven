package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        User user = new User(2L);
        List<String> commands = List.of(
                "MATHS", "/test", "Неправильный ответ", "/stop", "/back", "/statistic_general",
                "ENGLISH", "/test", "ПрОсТой", "/next", "множество",
                                               "/next", "бла-бла", "/stop", "/back", "/statistic_general",
                "MATHS", "/test", "100", "/stop", "/back", "/statistic_general");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                        "RUSSIAN: нет информации, пройдите тест.\n" +
                        "ENGLISH: нет информации, пройдите тест.\n",
                fakeBot.getMessages().get(6));
        Assert.assertEquals("MATHS: 0 - правильных, 1 - неправильных\n" +
                        "RUSSIAN: нет информации, пройдите тест.\n" +
                        "ENGLISH: 2 - правильных, 1 - неправильных\n",
                fakeBot.getMessages().get(17));
        Assert.assertEquals("MATHS: 1 - правильных, 0 - неправильных\n" +
                        "RUSSIAN: нет информации, пройдите тест.\n" +
                        "ENGLISH: 2 - правильных, 1 - неправильных\n",
                fakeBot.getMessages().get(24));
    }

    /**
     * Тест на корректную обработку предметной статистики
     */
    @Test
    public void testSubjectStatistic()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        User user = new User(3L);
        List<String> commands = List.of(
                "MATHS", "/statistic_subject", "/test", "Неправильный ответ", "/stop", "/statistic_subject",
                                               "/test", "100", "/stop", "/statistic_subject", "/back",
                "RUSSIAN", "/test", "A", "/stop", "/statistic_subject");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        Assert.assertEquals("MATHS: Информации нет. Пройдите тест.",
                fakeBot.getMessages().get(1));
        Assert.assertEquals("MATHS: попытка №1: 0 - правильных, 1 - неправильных\n",
                fakeBot.getMessages().get(6));
        Assert.assertEquals("MATHS: попытка №1: 0 - правильных, 1 - неправильных\n" +
                                    "MATHS: попытка №2: 1 - правильных, 0 - неправильных\n",
                fakeBot.getMessages().get(11));
        Assert.assertEquals("RUSSIAN: попытка №1: 0 - правильных, 1 - неправильных\n",
                fakeBot.getMessages().get(18));
    }

    /**
     * Проверка начала формирования статистики при запуске теста
     * (запуск теста фиксируется, даже если пользователь не начал отвечать на вопросы)
     */
    @Test
    public void unfinishedTest(){
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        User user = new User(1L);
        List<String> commands = List.of(
                "MATHS", "/test", "/stop", "/statistic_subject");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        Assert.assertEquals("MATHS: попытка №1: ",
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 1));
    }
}
