package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;
import java.util.List;



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
    /** Поле сообщения типа Message для отправки Update */
    private Message message = new Message();
    /** Поле типа Chat для установки id */
    private Chat chat = new Chat();
    /** Поле списка команд для отправки боту */
    private String[] commands;
    /** Поле типа Update для отправки запросов боту */
    private Update update = new Update();

    /**
     * Инициализация нового бота
     */
    @BeforeEach
    public void init() throws TelegramApiException {
        bot = Mockito.spy(new TelegramBot());
        bot.setBehavior(Mockito.spy(new Behavior(bot)));
        Mockito.doReturn(null).when(bot).execute(Mockito.any(SendMessage.class));
        Mockito.doNothing().when(bot).sendMessage(Mockito.any(Long.class), Mockito.anyString());
        Mockito.doNothing().when(bot).sendMessageWithButtons(
                Mockito.any(Long.class), Mockito.anyString(), Mockito.anyString());
        chat.setId(chatId);
        message.setChat(chat);
    }

    /**
     * Проверка бота на получение update
     *
     * @result Сообщения новых пользователей принимаются корректно
     */
    @Test
    public void checkMessage(){
        chat.setId(chatId);
        message.setChat(chat);
        message.setText("/start");
        update.setMessage(message);
        bot.onUpdateReceived(update);
        Mockito.verify(bot).sendMessage(chatId, "Привет, работяга!");
    }

    /**
     * Проверка оповещения о завершении тестирования
     *
     * @result Выводится сообщение об отсутствии вопросов к тестированию
     */
    @Test
    public void checkMessageAfterTest(){
        commands = new String[]{"ENGLISH", "/test", "/next"};
        for (int i=0; i<151; i++){
            message.setText(commands[Math.min(i, 2)]);
            update.setMessage(message);
            bot.onUpdateReceived(update);
        }
        Mockito.verify(bot).sendMessage(chatId, "Вопросов нет. \nДля продолжения отправьте /start");
    }

    /**
     * Проверка сохранения списка вопросов к повторению для каждого предмета
     *
     * @result при смене предмета сохранился вопрос, на который был дан неправильный ответ
     */
    @Test
    public void savingWhenChanging(){
        commands = new String[]{"MATHS", "/test", "la-la-la", "/stop", "ENGLISH", "MATHS", "/repeat"};
        for (String command : commands) {
            message.setText(command);
            update.setMessage(message);
            bot.onUpdateReceived(update);
        }
        Mockito.verify(bot, Mockito.times(2))
                .sendMessage(chatId, "Вычислите степень: 10^2");
    }

    /**
     * Проверка своевременной рассылки напоминания большому числу пользователей
     * @throws InterruptedException
     */
    @Test
    public void remind() throws InterruptedException {
        message.setText("/start");
        List<Long> fakeIds = new LinkedList<>();
        TimerBehavior.setStandardDispatchTime(500);
        for (long i = 0L; i<30; i++) {
            chat.setId(i);
            message.setChat(chat);
            update.setMessage(message);
            bot.onUpdateReceived(update);
            fakeIds.add(i);
        }
        Thread.sleep(500);
        for (Long fakeId : fakeIds){
            Mockito.verify(bot).sendMessageWithButtons(fakeId, "Вас давно не было видно. Хотите пройти тест?",
                    "SUBJECT_BOARD");
        }
    }

    /**
     * Отладочный тест для проверки того, что, после прохождения теста по одному предмету, результат
     * последней попытки не переносится в статистику другого предмета (больная мозоль)
     */
    @Test
    public void checkUserStatisticsLogic(){
        commands = new String[]{"MATHS", "/test", "100", "/stop", "ENGLISH", "/statistic_subject"};
        for (String command : commands){
            message.setText(command);
            update.setMessage(message);
            bot.onUpdateReceived(update);
        }
        Mockito.verify(bot).sendMessage(chatId, "ENGLISH: Информации нет. Пройдите тест.");
    }
}