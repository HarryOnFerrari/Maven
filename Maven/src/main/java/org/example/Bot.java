package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.nio.file.Files;

import java.io.IOException;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private String HELP = "Це бот для обучения. " +
            "Сейчас ты можешь проходить здесь тесты и проверять свой скилл. \n" +
            "Просто используй: \n" +
            "/test - для запуска теста \n" +
            "/next - для перехода к следующему вопросу \n" +
            "/stop - для завершения работы";

    @Override
    public String getBotUsername() {
        return "giveme100poinrsinbrs_bot";
    }

    @Override
    public String getBotToken() {
        return "2079838287:AAEdcvZtKJc8_zF2Ej8YJa94IDVuXAs25zY";
    }

    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            // setChatId - id клиента, обратившегося к боту
            //message.setText(update.getMessage().getText());
            //message.setText("Хорошего дня, реальный кабан!");

            switch (update.getMessage().getText()){
                case ("/start"):
                    message.setText("Привет, работяга!");
                    break;

                case ("/test"):
                    message.setText("И тут начинаются тесты");
                    Testing test = new Testing();
                    //test.makeTest(update);
                    try {
                        Path path = Path.of("C:\\Users\\Даша\\Desktop\\BOT\\Maven\\src\\main\\java\\org\\example\\Tests.txt");
                        List<String> list = Files.readAllLines(path);
                        String stroka = "";
                        for (int i=0; i<list.size();i++)
                            stroka += list.get(i) +"\n";
                        message.setText(stroka);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case ("/help"):
                    message.setText(HELP);
                    break;

                case ("/stop"):
                    message.setText("Эта функция будет реализована в будущих версиях :)))");
                    break;

                default:
                    message.setText("Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                    break;
            }

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
