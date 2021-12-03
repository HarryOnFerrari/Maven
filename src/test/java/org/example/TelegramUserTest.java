package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TelegramUserTest {
    private User user;
    Field result;
    IBot bot = new IBot() {
        public String lastMessage;
        @Override
        public void setMessage(Long id, String message) {returner(message);}
        @Override
        public void setMessageWithButtons(Long id, String message, String keyboardLayout) {returner(message);}

        public void returner(String message){lastMessage = message;}
    };
    private Behavior behavior = new Behavior(bot);

    @BeforeAll
    public void init() throws NoSuchFieldException {
        result= bot.getClass().getDeclaredField("lastMessage"); // получаем доступ к полю lastMessage
        result.setAccessible(true); // рахрешаем работу с этим полем
    }

    /**
     * Проверка оповещения о завершении тестирования
     *
     * @result Выводится сообщение об отсутствии вопросов к тестированию
     */
    @Test
    public void checkMessageAfterTest() throws IllegalAccessException {
        User user = new User(537446876L);
        behavior.readCommands(user, "/start");
        behavior.readCommands(user, "SUBJECT");
        behavior.readCommands(user, "ENGLISH");
        behavior.readCommands(user, "/test");
        for (int i=0; i<151; i++){
            behavior.readCommands(user, "/next");
        }
        String lastMessage = (String) result.get(bot);
        Assertions.assertEquals(lastMessage, "Вопросов нет. \nДля продолжения отправьте /start");
    }

    /**
     * Проверка сохранения списка вопросов к повторению для каждого предмета
     *
     * @result при смене предмета сохранился вопрос, на который был дан неправильный ответ
     */
    @Test
    public void savingWhenChanging() throws IllegalAccessException {
        User user = new User(537446876L);
        String[] messages = {"MATHS", "/test", "la-la-la", "/stop", "ENGLISH", "MATHS", "/repeat"};
        for (String message : messages)
            behavior.readCommands(user, message);
        String lastMessage = (String) result.get(bot);
        Assertions.assertEquals(lastMessage, "Вычислите степень: 10^2");
    }

    /**
     * Проверка своевременной рассылки напоминания большому числу пользователей
     * @throws InterruptedException
     */
    /*@Test
    public void remind() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Field timeBehavior = TimerBehavior.class.getDeclaredField("standardDispatchTime");
        timeBehavior.setAccessible(true);
        timeBehavior.set(TimerBehavior.class, 500);

        int count = 30;
        for (long i = 0L; i<count; i++) {
            User fakeUser = new User(i);
            behavior.readCommands(fakeUser, "timerOn");
        }
        Thread.sleep(500);
        String reminder;
        for (int i=0; i<count; i++){
            reminder = (String) result.get(bot);
            Assert.assertEquals(reminder, "Вас давно не было видно. Хотите пройти тест?");
        }
    }*/
}
