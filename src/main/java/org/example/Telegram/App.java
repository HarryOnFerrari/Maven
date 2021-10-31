package Telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


/**
 * Запуск бота.
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class App 
{
    /**
     * Основной метод запуска.
     * @see Bot
     * @params args - параметры командной строки
     */
    public static void main( String[] args ){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
