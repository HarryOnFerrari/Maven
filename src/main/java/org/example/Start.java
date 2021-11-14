package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Scanner;

public class Start {
    public static void main(String[] args)
    {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot().telegram);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        ConsoleBot consoleBot = new ConsoleBot(new Scanner(System.in), System.out);
        consoleBot.run();
    }
}
