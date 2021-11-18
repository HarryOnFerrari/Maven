package org.example;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Scanner;

public class Start {
    public static void main(String[] args){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        Thread consoleBot = new Thread() {
            public void run(){new ConsoleBot(new Scanner(System.in),System.out).run();}
        };
        Thread vkBot = new Thread() {
            public void run(){
                try {
                    new BotsLongPoll(new VKBot()).run();
                } catch (BotsLongPollHttpException e) {
                    e.printStackTrace();
                } catch (BotsLongPollException e) {
                    e.printStackTrace();
                }
            }
        };
        consoleBot.start();
        vkBot.start();
    }
}
