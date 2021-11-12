package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

    public void overrideUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (!bot.users.containsKey(update.getMessage().getChatId())) {
                bot.users.put(update.getMessage().getChatId(), new User(update.getMessage().getChatId()));
            }
            User user = bot.users.get(update.getMessage().getChatId());
            bot.readCommands(user, update.getMessage().getText());
        }
    }

    /**
     * Инициализация нового бота
     */
    @BeforeEach
    public void init() throws TelegramApiException {
        bot = Mockito.spy(new TelegramBot());
        bot.telegram = Mockito.spy(new Registration());
        Mockito.doReturn(null).when(bot.telegram).execute(Mockito.any(SendMessage.class));
        Mockito.doNothing().when(bot).setMessage(Mockito.any(Long.class), Mockito.anyString());
        Mockito.doNothing().when(bot).setMessage(
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
        bot.telegram.onUpdateReceived(startUpdate);
        Mockito.verify(bot.telegram).onUpdateReceived(startUpdate);
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
        for (Integer i=0; i<151; i++){
            overrideUpdateReceived(update);
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
            overrideUpdateReceived(update);
        Mockito.verify(bot, Mockito.times(2))
                .setMessage(chatId, "Вычислите степень: 10^2");
    }
}
