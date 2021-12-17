package org.example;

import java.util.List;

import org.checkerframework.checker.units.qual.A;
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
     * Тест на неправильный ответ
     */
    @Test
    public void wrongAnswer(){
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        behavior.processCommand(user, "MATHS");
        behavior.processCommand(user, "/test");
        behavior.processCommand(user, "Неправильный ответ");
        Assert.assertEquals("Вы ошиблись, верный ответ: 100",
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

    /**
     * Тест на сохранение всех впопросов, на которые был дан неправильный ответ
     */
    @Test
    public void saveAllWrongAnswers(){
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("ENGLISH", "/test", "простой", "/next", "не_множество",
                "/next", "чушь", "/next", "не_украшения", "успех", "/stop", "/repeat");
        for (String command : commands)
            behavior.processCommand(user, command);

        Assert.assertEquals("Переведите на русский: trappings",
                fakeBot.getMessages().get(fakeBot.getMessages().size()-1));
        behavior.processCommand(user, "украшения");
        behavior.processCommand(user, "/next");
        Assert.assertEquals("Переведите на русский: plenty",
                fakeBot.getMessages().get(fakeBot.getMessages().size()-1));
        behavior.processCommand(user, "множество");
        behavior.processCommand(user, "/next");
        Assert.assertEquals("Вопросов нет. \n" +
                        "Для продолжения отправьте /start",
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 1));
    }

    /**
     * Тест на команду  /next после всех вопросов теста
     */
    @Test
    public void testIsOver(){
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        behavior.processCommand(user, "MATHS");
        behavior.processCommand(user, "/test");
        for (int i = 10; i<=29; i++) {
            behavior.processCommand(user, String.valueOf(i^2));
            behavior.processCommand(user, "/next");
        }
        Assert.assertEquals("Вопросы закончились\n" +
                        "Если хотите выйти из режима теста, введите /stop\n" +
                        "Если хотите отработать вопросы с ошибкой, то введите /next",
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 1));
    }

    /**
     * Тестирование на верное завершение режима повторения, когда вопросы закончились или не был пройден тест
     */
    @Test
    public void correctEndWhenRepeating()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("MATHS", "/repeat", "MATHS", "/test", "Неправильный ответ", "/stop",
                "MATHS", "/repeat", "100", "/next");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }

        String correctEndRepeating = "Вопросов нет. \n" +
                            "Для продолжения отправьте /start";
        Assert.assertEquals(correctEndRepeating,
                fakeBot.getMessages().get(1));
        Assert.assertEquals(correctEndRepeating,
                fakeBot.getMessages().get(10));
    }

    /**
     * Тестирование на корректную работу команды /back
     */
    @Test
    public void correctBack()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("/start", "SUBJECT", "MATHS", "/test", "la-la-la", "/stop", "/back");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        String correctAnswerAfterBack = "Выберите предмет:";
        Assert.assertEquals(correctAnswerAfterBack,
                fakeBot.getMessages().get(2));
        Assert.assertEquals(correctAnswerAfterBack,
                fakeBot.getMessages().get(8));
    }

    /**
     * Тестирование на корректное отображение списка предметов
     */
    @Test
    public void listSubjects()
    {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        List<String> commands = List.of("/start", "SUBJECT");
        for (String command : commands)
        {
            behavior.processCommand(user, command);
        }
        Assert.assertEquals("1.Математика\n" +
                                    "2.Английский язык\n" +
                                    "3.Русский язык\n" +
                                    "меню\n" +
                                    "статистика\n",
                fakeBot.getKeyboard().get(1));
    }

    /**
     * Отладочный тест для проверки того, что, после прохождения теста по одному предмету, результат
     * последней попытки не переносится в статистику другого предмета (больная мозоль)
     */
    @Test
    public void checkUserStatisticsLogic(){
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        User user = new User(0L);
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
    public void checkRemind() throws InterruptedException {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        TimerBehavior.setStandardDispatchTime(10);
        int users = 10;
        for (long i = 0L; i<users; i++) {
            User user = new User(i);
            behavior.processCommand(user, "timerOn");
        }
        Thread.sleep( 10 + users*20); // отправка 1 сообщения у fakeBot занимет примерно 20ms
        String message = "Вас давно не было видно. Хотите пройти тест?";
        for (int i=0; i<users; i++)
            Assert.assertEquals(message,
                    fakeBot.getMessages().get(fakeBot.getMessages().size() - i -1));
    }

    /**
     * Тест на корректное включение и получение уведомлений
     * после смены режима с "получать"(предустановленный) -> "не получать" -> "получать"
     * @throws InterruptedException
     */
    @Test
    public void checkAfterDisagree() throws InterruptedException {
        FakeBot fakeBot = new FakeBot();
        Behavior behavior = new Behavior(fakeBot);
        TimerBehavior.setStandardDispatchTime(10);
        behavior.processCommand(user, "timerOff");
        Thread.sleep(10 + 20);
        for (String message : fakeBot.getMessages())
            Assert.assertNotEquals("Вас давно не было видно. Хотите пройти тест?", message);
        behavior.processCommand(user, "timerOn");
        Thread.sleep(10 + 20);
        Assert.assertEquals("Вас давно не было видно. Хотите пройти тест?",
                fakeBot.getMessages().get(fakeBot.getMessages().size() - 1));
    }
}
