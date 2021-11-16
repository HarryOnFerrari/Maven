package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class VKBot extends LongPollBot implements IBot{
    public HashMap<Long, User> users = new HashMap<>();
    private IBot bot;
    public Behavior behavior = new Behavior(this);

    @Override
    public String getAccessToken() {
        String token = null;
        Properties prop = new Properties();
        try {
            prop.load(TelegramBot.class.getClassLoader().getResourceAsStream("config.properties"));
            token = prop.getProperty("tokenVK");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return token;
    }

    @Override
    public int getGroupId() {
        return 208898778;
    }


    @Override
    public void onMessageNew(MessageNewEvent command) {
        Message message = command.getMessage();
        try {
            new MessagesSend(this)
                    .setPeerId(message.getPeerId())
                    .setMessage("В сообщении есть вложение!")
                    .execute();
        } catch ( BotsLongPollHttpException | BotsLongPollException e) {
            e.printStackTrace();
        }

    }

        /**
         * Функция для отправки сообщения пользователю.
         *  @param id      - id чата, в который требуется отправить сообщение
         * @param message - текст сообщения
         */
    @Override
    public void setMessage(Long id, String message) {

    }

    /**
     * Функция для отправки сообщения с кнопками пользователю.
     *
     * @param id             - адрес отправки
     * @param message        - сообщение
     * @param keyboardLayout - вариант шаблона клавиатуры
     */
    @Override
    public void setMessageWithButtons(Long id, String message, String keyboardLayout) {

    }
}
