package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Класс Бота для telegram.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TelegramBot extends TelegramLongPollingBot implements IBot {
    /** Поле списка пользователей */
    private HashMap<Long, User> users = new HashMap<>();

    /**
     * Функция получения значения пользовательского имени бота.
     * @return пользовательское имя бота
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
     * @param update - обновление состояния
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (!users.containsKey(update.getMessage().getChatId()))
                users.put(update.getMessage().getChatId(), new User(update.getMessage().getChatId()));
            User user = users.get(update.getMessage().getChatId());
            readCommands(user, update.getMessage().getText());
        }
        else if (update.hasCallbackQuery()){
            User user = users.get(update.getCallbackQuery().getMessage().getChatId());
            readCommands(user, update.getCallbackQuery().getData());
        }
    }

    @Override
    public void setMessage(Long id, String message, String flag) {
        SendMessage newMessage = new SendMessage();
        newMessage.setChatId(id.toString());
        newMessage.setText(message);
        newMessage.setReplyMarkup(ButtonsForTelegram.valueOf(flag).value());
        try {
            execute(newMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция для отправки сообщения пользователю.
     *
     * @see IBot#setMessage(Long, String)
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     * @return
     */
    @Override
    public void setMessage(Long id, String message){
        SendMessage newMessage = new SendMessage();
        newMessage.setChatId(id.toString());
        newMessage.setText(message);

        try {
            execute(newMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
