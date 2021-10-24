package Telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.Console;

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
       // System.out.print("Hello dog");
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
