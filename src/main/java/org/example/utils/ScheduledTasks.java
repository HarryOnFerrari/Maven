package org.example.utils;

import org.example.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.net.MalformedURLException;
import java.net.URL;

//@EnableScheduling
public class ScheduledTasks implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Yo!");
    }

    //TelegramBot telegramBot;

    //@Scheduled(cron = "*/1 * * * * ")
    /*public URL sendPicture(){
        URL picture = null;
        //picture = getClass().getClassLoader().getResourceAsStream("канашка.png");
        try {
            picture = new URL("https://sun9-9.userapi.com/impg/su8-wM6u1gRHJ77y1TIQAecpvn6qE6SywTcJrg/VV2qIxiDnlE.jpg?size=945x171&quality=96&sign=61a7e0c7dc4f92bdc52f5c2b7194f4fa&type=album");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //System.out.println(picture);
        //for (Long user : telegramBot.users.keySet()){
            // в классе TelegramBot появился метод SetPicture, там возникла проблема в setPhoto - метод требует InputFile
            // короче, надо как-то правильно прочитать ресурс и спарсить в InputStream
            //telegramBot.setPicture(user, picture.toString());
        //}
        //setPicture(picture);
        return picture;
    }*/
}
