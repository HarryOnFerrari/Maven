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
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 1));
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

    /**
     * Отладочный тест для проверки того, что, после прохождения теста по одному предмету, результат
     * последней попытки не переносится в статистику другого предмета (больная мозоль)
     */
    @Test
    public void checkUserStatisticsLogic(){
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("MATHS", "/test", "100", "/stop", "ENGLISH", "/statistic_subject");
        for (String command : commands){
            behavior.processCommand(user, command);
        }
        Assert.assertEquals("ENGLISH: Информации нет. Пройдите тест.",
                fakeBot.getMessages().get(fakeBot.getMessages().size()-1));
    }

    /**
     * Проверка своевременной рассылки напоминания большому числу пользователей
     */
    @Test
    public void remind() throws InterruptedException {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        TimerBehavior.setStandardDispatchTime(10);
        int users = 10;
        for (long i = 0L; i<users; i++) {
            User user = new User(i);
            behavior.processCommand(user, "timerOn");
        }
        Thread.sleep( 10 + users*20); // отправка 1 сообщения у fakeBot занимет примерно 20ms
        String questionWithWrongAnswer = "Вас давно не было видно. Хотите пройти тест?";
        Assert.assertEquals(questionWithWrongAnswer,
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 2));
        Assert.assertEquals(questionWithWrongAnswer,
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 30 - 2));
    }
}
