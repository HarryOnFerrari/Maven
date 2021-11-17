package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.basic.Message;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

public class VKBot extends LongPollBot implements IBot{
    private TransportClient transportClient = new HttpTransportClient();
    private VkApiClient vk = new VkApiClient(transportClient);
    private GroupActor actor = new GroupActor(getGroupId(), getAccessToken());
    private Random random = new Random();
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
        Long userId = (long)(message.getPeerId());
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId));
        }
        User user = users.get(userId);
        behavior.readCommands(user, message.getText());
        /*try {
            new MessagesSend(this)
                    .setPeerId(message.getPeerId())
                    .setMessage("В сообщении есть вложение!")
                    .execute();
        } catch ( BotsLongPollHttpException | BotsLongPollException e) {
            e.printStackTrace();
        }*/

    }

        /**
         * Функция для отправки сообщения пользователю.
         *  @param id      - id чата, в который требуется отправить сообщение
         * @param message - текст сообщения
         */
    @Override
    public void setMessage(Long id, String message) {
        try {
            new MessagesSend(this)
                    .setPeerId(Math.toIntExact(id))
                    .setMessage(message)
                    .execute();
        } catch ( BotsLongPollHttpException | BotsLongPollException e) {
            e.printStackTrace();
        }
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
        try {
            vk.messages().send(actor).message(message).userId(Math.toIntExact(id)).randomId(random.nextInt(10000)).keyboard(ButtonsForVK.valueOf(keyboardLayout).value()).execute();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }

        /*
        * SendMessage newMessage = new SendMessage();
            newMessage.setChatId(id.toString());
            newMessage.setText(message);
            newMessage.setReplyMarkup(ButtonsForTelegram.valueOf(keyboardLayout).value());
            try {
                execute(newMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }*/
    }
}