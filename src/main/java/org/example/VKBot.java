package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.constants.CommandConstants.*;

public class VKBot extends LongPollBot implements IBot{
    /** Поле, через которое передаются наши запросы*/
    private TransportClient transportClient = new HttpTransportClient();
    /** Поле взаимодействия с VK-API с помощью запросов*/
    private VkApiClient vk = new VkApiClient(transportClient);
    /** Поле авторизации сообщества*/
    private GroupActor actor = new GroupActor(getGroupId(), getAccessToken());
    private Random random = new Random();
    /** Поле списка пользователей */
    public Map<Long, User> users = new HashMap<>();
    /** Поле поведения бота для обработки команд */
    public Behavior behavior = new Behavior(this);

    /**
     * Функция получения значения токена бота.
     * @return токен бота
     */
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

    /**
     * Функция получения id группы бота
     * @return id группы
     */
    @Override
    public int getGroupId() {
        Integer groupId = null;
        Properties prop = new Properties();
        try {
            prop.load(TelegramBot.class.getClassLoader().getResourceAsStream("config.properties"));
            groupId = Integer.parseInt(prop.getProperty("groupIdVK"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return groupId;
    }

    /**
     * Функция, которая принимает и обрабатывает обновления состояния бота.
     * @param command - новое событие для обработки
     */
    @Override
    public void onMessageNew(MessageNewEvent command) {
        Message message = command.getMessage();
        Long userId = (long)(message.getPeerId());
        String commandText;
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId));
        }
        User user = users.get(userId);
        user.reminder.setReminder(this);
        if (message.getPayload() != null){
            Matcher textMatch = Pattern.compile("\\{.+?:\"\\\\?(.+?)\"}").matcher(message.getPayload());
            textMatch.find();
            commandText = textMatch.group(1);
        }
        else
            commandText = message.getText();
        if (commandText.equals(SETTING))
            setMessageWithButtons(userId, TIMER_SETTING_ON, "SETTING_BOARD_ON");
        else
            behavior.readCommands(user, commandText);
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
            vk.messages().send(actor).message(message)
                    .userId(Math.toIntExact(id))
                    .randomId(random.nextInt(10000))
                    .keyboard(ButtonsForVK.valueOf(keyboardLayout).value()).execute();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }
}
