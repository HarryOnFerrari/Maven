package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.Properties;
/**
 * Класс Бота для регистрации его в Telegram.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class RegistrationTelegram extends TelegramLongPollingBot {
    /** Поле экземпляра класса TelegramBot*/
    TelegramBot bot;
    /** Конструктор класса*/
    public RegistrationTelegram(TelegramBot bot){
        this.bot = bot;
    }

    /**
     * Функция получения имени бота.
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "giveme100poinrsinbrs_bot";
    }

    /**
     * Функция получения значения токена бота.
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        String token = null;
        Properties prop = new Properties();
        try {
            prop.load(TelegramBot.class.getClassLoader().getResourceAsStream("config.properties"));
            token = prop.getProperty("token");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return token;
    }

    /**
     * Функция, которая принимает и обрабатывает обновления состояния бота.
     *
     * @param update - состояние отдельного пользователя
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (!bot.users.containsKey(update.getMessage().getChatId())) {
                bot.users.put(update.getMessage().getChatId(), new User(update.getMessage().getChatId()));
            }
            User user = bot.users.get(update.getMessage().getChatId());
            user.setReminder(bot);
            bot.readCommands(user, update.getMessage().getText());
        } else if (update.hasCallbackQuery()) {
            User user = bot.users.get(update.getCallbackQuery().getMessage().getChatId());
            user.setReminder(bot);
            bot.readCommands(user, update.getCallbackQuery().getData());
        }
    }
}

