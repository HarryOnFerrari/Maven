package org.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Timer;

/**
 * Тесты на класс {@link TimerBehavior}
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TimerBehaviorTest {
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
     * Тест на корректное поведение таймера
     */
    @Test
    public void testBehaviorReminder()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("/start", "timerOff", "timerOn", "timer_off_2");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        //System.out.println(reminder.getOffsetReceiveNotifications());
        Assert.assertEquals("Уведомления выключены", fakeBot.getMessages().get(2));
        Assert.assertEquals("Уведомления успешно включены", fakeBot.getMessages().get(4));
        Assert.assertEquals("Уведомления выключены", fakeBot.getMessages().get(6));
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

    /**
     * Тест на корректное включение и получение уведомлений
     * @throws InterruptedException
     */
    @Test
    public void checkAfterDisagree() throws InterruptedException {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        TimerBehavior.setStandardDispatchTime(10);
        User user = new User(0L);
        behavior.processCommand(user, "timerOff");
        Thread.sleep(10 + 20);
        Assert.assertNotEquals("Вас давно не было видно. Хотите пройти тест?",
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 1));
        behavior.processCommand(user, "timerOn");
        Thread.sleep(10 + 20);
        Assert.assertEquals("Вас давно не было видно. Хотите пройти тест?",
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 1));
    }
}
