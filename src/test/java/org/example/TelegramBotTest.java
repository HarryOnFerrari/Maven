package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;
import java.util.List;

import static org.example.TimerBehavior.*;


/**
 * junit тестирование класса Bot
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TelegramBotTest {
    /** Поле mock бота */
    private TelegramBot bot;
    /** Поле id чата */
    private Long chatId = 138239293L;

    /**
     * Инициализация нового бота
     */
    @BeforeEach
    public void init() throws TelegramApiException {
        bot = Mockito.spy(new TelegramBot());
        bot.behavior = Mockito.spy(new Behavior(bot));
        Mockito.doReturn(null).when(bot).execute(Mockito.any(SendMessage.class));
        Mockito.doNothing().when(bot).setMessage(Mockito.any(Long.class), Mockito.anyString());
        Mockito.doNothing().when(bot).setMessageWithButtons(
                Mockito.any(Long.class), Mockito.anyString(), Mockito.anyString());
    }

    /**
     * Проверка бота на получение update
     *
     * @result Сообщения новых пользователей принимаются корректно
     */
    @Test
    public void checkMessage(){
        Update startUpdate = new Update();
        Message startMessage = Mockito.mock(Message.class);
        Mockito.when(startMessage.getChatId()).thenReturn(chatId);
        Mockito.when(startMessage.getText()).thenReturn("/test");
        startUpdate.setMessage(startMessage);
        bot.onUpdateReceived(startUpdate);
        Mockito.verify(bot).onUpdateReceived(startUpdate);
    }

    /**
     * Проверка оповещения о завершении тестирования
     *
     * @result Выводится сообщение об отсутствии вопросов к тестированию
     */
    @Test
    public void checkMessageAfterTest(){
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.hasText()).thenReturn(true);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn("ENGLISH").thenReturn("/test").thenReturn("/next");
        update.setMessage(message);
        for (int i=0; i<151; i++){
            bot.onUpdateReceived(update);
        }
        Mockito.verify(bot).setMessage(chatId, "Вопросов нет. \nДля продолжения отправьте /start");
    }

    /**
     * Проверка сохранения списка вопросов к повторению для каждого предмета
     *
     * @result при смене предмета сохранился вопрос, на который был дан неправильный ответ
     */
    @Test
    public void savingWhenChanging(){
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.hasText()).thenReturn(true);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn("MATHS").thenReturn("/test").thenReturn("la-la-la").
                thenReturn("/stop").thenReturn("ENGLISH").thenReturn("MATHS").thenReturn("/repeat");
        update.setMessage(message);
        for (int i=0; i<7; i++)
            bot.onUpdateReceived(update);
        Mockito.verify(bot, Mockito.times(2))
                .setMessage(chatId, "Вычислите степень: 10^2");
    }

    /**
     * Проверка своевременной рассылки напоминания большому числу пользователей
     * @throws InterruptedException
     */
    /*@Test
    public void remind() throws InterruptedException {
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.hasText()).thenReturn(true);
        Mockito.when(message.getText()).thenReturn("/start");
        List<Long> fakeIds = new LinkedList<>();
        for (long i = 0L; i<30; i++)
            fakeIds.add(i);
        TimerBehavior.standardDispatchTime=500;
        for (Long fakeId : fakeIds) {
            Mockito.when(message.getChatId()).thenReturn(fakeId);
            update.setMessage(message);
            bot.onUpdateReceived(update);
        }
        Thread.sleep(500);
        for (Long fakeId : fakeIds){
            Mockito.verify(bot).setMessageWithButtons(fakeId, "Вас давно не было видно. Хотите пройти тест?",
                    "SUBJECT_BOARD");
        }
    }*/

    /**
     * Отладочный тест для проверки того, что, после прохождения теста по одному предмету, результат
     * последней попытки не переносится в статистику другого предмета (больная мозоль)
     */
    @Test
    public void checkUserStatisticsLogic(){
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.hasText()).thenReturn(true);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn("MATHS").thenReturn("/test").thenReturn("100").
                thenReturn("/stop").thenReturn("ENGLISH").thenReturn("/statistic_subject");
        update.setMessage(message);
        for (int i=0; i<6; i++)
            bot.onUpdateReceived(update);
        Mockito.verify(bot).setMessage(chatId, "ENGLISH: Информации нет. Пройдите тест.");
    }
}