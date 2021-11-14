package org.example.utils;

import org.example.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URL;

@EnableScheduling
public class ScheduledTasks {

    TelegramBot telegramBot;

    @Scheduled(cron = "0 0 3 * * ?")
    public void sendPicture(){
        URL picture;
        picture = getClass().getResource("канашка.png");
        for (Long user : telegramBot.users.keySet()){
            // в классе TelegramBot появился метод SetPicture, там возникла проблема в setPhoto - метод требует InputFile
            // короче, надо как-то правильно прочитать ресурс и спарсить в InputStream
            telegramBot.setPicture(user, picture);
        }
    }
}
