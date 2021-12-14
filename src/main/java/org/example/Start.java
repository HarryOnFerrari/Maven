package org.example;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import org.example.platforms.ConsoleBot;
import org.example.platforms.TelegramBot;
import org.example.platforms.VKBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Scanner;

/**
 * Класс запуска.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Start {
    /**
     * Метод, запускающий ботов.
     */
    public static void main(String[] args){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        Thread consoleBot = new Thread(() -> new ConsoleBot(new Scanner(System.in),System.out).run());
        Thread vkBot = new Thread(() -> {
            try {
                new BotsLongPoll(new VKBot()).run();
            } catch (BotsLongPollHttpException | BotsLongPollException e) {
                e.printStackTrace();
            }
        });
        consoleBot.start();
        vkBot.start();
    }
}