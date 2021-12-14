package org.example;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Тесты на класс {@link Behavior}
 *
 * @author Пыжьянов Вячеслав
 * @since 07.12.2021
 */
public class BehaviorTest
{
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
     * Тест на корректное стартовое сообщение
     */
    @Test
    public void testStartMessage()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        behavior.processCommand(user, "/start");
        Assert.assertEquals("[Привет, работяга!, Меню: ]",
                fakeBot.getMessages().toString());
    }

    /**
     * Проверка на то, что правильный ответ будет принят с любым регистром написания
     */
    @Test
    public void testCorrectAnswerForAnyRegister()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        behavior.processCommand(user, "ENGLISH");
        behavior.processCommand(user, "/test");
        behavior.processCommand(user, "ПрОсТоЙ");
        Assert.assertEquals("Правильный ответ!",
                fakeBot.getMessages().get(2));
    }

    /**
     * Тестирование, сохранения вопроса, на который был дан неправильный ответ
     */
    @Test
    public void savingWhenChanging()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("MATHS", "/test", "Неправильный ответ", "/stop",
                "MATHS", "/repeat");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        String questionWithWrongAnswer = "Вычислите степень: 10^2";
        Assert.assertEquals(questionWithWrongAnswer,
                fakeBot.getMessages().get(1));
        Assert.assertEquals(questionWithWrongAnswer,
                fakeBot.getMessages().get(6));
    }
}
