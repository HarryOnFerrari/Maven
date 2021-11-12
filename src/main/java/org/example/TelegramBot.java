package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

/**
 * Класс Бота для telegram.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TelegramBot extends Registration  {
    /** Поле списка пользователей */
    public HashMap<Long, User> users = new HashMap<>();
    public Behavior b = new Behavior() {
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
                    setMessage(id, "", "MODE");
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        /**
         * Функция для отправки сообщения с кнопками пользователю.
         *
         * @see IBot#setMessage(Long, String, String)
         * @see ButtonsForTelegram
         * @param id - id чата, в который требуется отправить сообщение
         * @param message - текст сообщения
         * @param flag - вариант шаблона клавиатуры
         */
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
    };

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
            b.readCommands(user, update.getMessage().getText());
        }
        else if (update.hasCallbackQuery()){
            User user = users.get(update.getCallbackQuery().getMessage().getChatId());
            b.readCommands(user, update.getCallbackQuery().getData());
        }
    }
}
