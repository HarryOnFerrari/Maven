package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

/**
 * Класс Бота для telegram.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TelegramBot extends Behavior {
    /** Поле списка пользователей */
    public HashMap<Long, User> users = new HashMap<>();
    /** Поле, реализующее функционал Telegram */
    RegistrationTelegram telegram = new RegistrationTelegram(this);
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
            telegram.execute(newMessage);
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
            telegram.execute(newMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
