package org.example;
import org.example.Telegram.Bot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * junit тестирование класса Bot
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class BotTest {
    /** Поле mock бота */
    private Bot bot;
    /** Поле id чата */
    private Long chatId = 138239293L;

    /**
     * Инициализация нового бота
     */
    @BeforeEach
    public void init(){
        bot = Mockito.spy(new Bot());
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
        Mockito.when(message.getText()).thenReturn("/test").thenReturn("/next");
        update.setMessage(message);
        Mockito.doNothing().when(bot).setMessage(Mockito.any(Long.class), Mockito.anyString());
        bot.onUpdateReceived(update);
        for (Integer i=0; i<149; i++){
            bot.onUpdateReceived(update);
        }
        Mockito.verify(bot).setMessage(chatId, "Вопросов нет."); // сама цель теста
    }
}
