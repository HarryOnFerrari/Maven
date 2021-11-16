package org.example;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws BotsLongPollException, BotsLongPollHttpException {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        new BotsLongPoll(new VKBot()).run();

        ConsoleBot consoleBot = new ConsoleBot(new Scanner(System.in), System.out);
        consoleBot.run();
    }
}
