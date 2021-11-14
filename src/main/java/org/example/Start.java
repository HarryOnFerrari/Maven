package org.example;

import org.example.utils.Quartz;
import org.quartz.SchedulerException;
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
            Quartz.quartzRun();
        } catch (TelegramApiException | SchedulerException e) {
            e.printStackTrace();
        }

        ConsoleBot consoleBot = new ConsoleBot(new Scanner(System.in), System.out);
        consoleBot.run();
    }
}
