package org.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserStatisticTest {
    private static User user;

    /**
     * Общая подготовка для всех методов
     */
    @BeforeClass
    public static void prepare()
    {
        user = new User(0L);
    }
    /**
     * Тест на корректную обработку общей статистики
     */
    @Test
    public void testGeneralStatistic()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("MATHS", "/test", "Неправильный ответ", "/stop", "/back", "/statistic_general",
                "ENGLISH", "/test", "ПрОсТой", "/next", "множество", "/next", "бла-бла", "/stop", "/back", "/statistic_general",
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
     * Тест на корректное предметной статистики
     */
    @Test
    public void testSubjectStatistic()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("MATHS", "/test", "Неправильный ответ", "/stop", "/statistic_subject",
                                                 "/test", "100", "/stop", "/statistic_subject", "/back",
                                       "RUSSIAN", "/test", "A", "/stop", "/statistic_subject");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        Assert.assertEquals("MATHS: попытка №1: 0 - правильных, 1 - неправильных\n",
                fakeBot.getMessages().get(5));
        Assert.assertEquals("MATHS: попытка №1: 0 - правильных, 1 - неправильных\n" +
                        "MATHS: попытка №2: 1 - правильных, 0 - неправильных\n",
                fakeBot.getMessages().get(10));
        Assert.assertEquals("RUSSIAN: попытка №1: 0 - правильных, 1 - неправильных\n",
                fakeBot.getMessages().get(17));
    }
}
