package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Класс Бота для telegram.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TelegramBot extends TelegramLongPollingBot implements IBot{
    /** Поле списка пользователей */
    private Map<Long, User> users = new HashMap<>();

    /** Поле поведения бота для обработки команд */
    private Behavior behavior = new Behavior(this);
    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    /**
     * Функция, возвращающая имя бота
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        String botUsername = null;
        Properties prop = new Properties();
        try {
            prop.load(TelegramBot.class.getClassLoader().getResourceAsStream("config.properties"));
            botUsername = prop.getProperty("botUsernameTelegram");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return botUsername;
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
            token = prop.getProperty("tokenTelegram");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return token;
    }
    /**
     * Функция, которая принимает и обрабатывает обновления состояния бота.
     * @param update - состояние отдельного пользователя
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (!users.containsKey(update.getMessage().getChatId())) {
                users.put(update.getMessage().getChatId(), new User(update.getMessage().getChatId()));
            }
            User user = users.get(update.getMessage().getChatId());
            user.getReminder().setReminder(this);
            behavior.readCommands(user, update.getMessage().getText());
        } else if (update.hasCallbackQuery()) {
            User user = users.get(update.getCallbackQuery().getMessage().getChatId());
            user.getReminder().setReminder(this);
            behavior.readCommands(user, update.getCallbackQuery().getData());
        }
    }

    /**
     * Функция для отправки сообщения пользователю.
     *
     * @see IBot#setMessage(Long, String)
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     */
    @Override
    public void setMessage(Long id, String message) {
        SendMessage newMessage = new SendMessage();
        newMessage.setChatId(id.toString());
        newMessage.setText(message);
        try {
            execute(newMessage);
            if (message.equals("Вопросов нет."))
                setMessageWithButtons(id, "", "MODE");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция для отправки сообщения с кнопками пользователю.
     *
     * @see IBot#setMessageWithButtons(Long, String, String)
     * @see ButtonsForTelegram
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     * @param keyboardLayout - вариант шаблона клавиатуры
     */
    @Override
    public void setMessageWithButtons(Long id, String message, String keyboardLayout) {
        SendMessage newMessage = new SendMessage();
        newMessage.setChatId(id.toString());
        newMessage.setText(message);
        newMessage.setReplyMarkup(ButtonsForTelegram.valueOf(keyboardLayout).value());
        try {
            execute(newMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
