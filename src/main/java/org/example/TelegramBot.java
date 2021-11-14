package org.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Класс Бота для telegram.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TelegramBot extends Behavior implements Job {
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

    public void setPicture(Long id, URL photo) {
        String pict = "http://samp-stats.ru/web/userbar-15377.png";
        SendPhoto picture = new SendPhoto();
        picture.setChatId(id.toString());
        picture.setPhoto(new InputFile(photo.toString())); // вряд ли заработает
        try {
            telegram.execute(picture);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, наследуемый от Job
     * Когда вызывается исполнение задач Quartz, создается новый класс, наследующий Job,
     * поэтому список юзеров всегда пустой
     * Надо что-то думкать
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        URL picture = null;
        try {
            picture = new URL("https://sun9-9.userapi.com/impg/su8-wM6u1gRHJ77y1TIQAecpvn6qE6SywTcJrg/VV2qIxiDnlE.jpg?size=945x171&quality=96&sign=61a7e0c7dc4f92bdc52f5c2b7194f4fa&type=album");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(picture.toString()));
        if (users.isEmpty())
            System.out.println("Loshara"); // к сожалению, оно так
        else
            System.out.println("Hmmm");
        for (Long user : users.keySet()){
            sendPhoto.setChatId(user.toString());
            try {
                telegram.execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
