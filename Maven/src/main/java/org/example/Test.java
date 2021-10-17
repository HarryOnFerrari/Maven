package org.example;

import java.io.*;
import java.util.Locale;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Test {

    public static void makeTest(Update update) {
        Bot executer = new Bot();
        try {
            File file = new File("Tests.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line;
            while (!update.getMessage().getText().equals("/stop")) {
                line = reader.readLine();
                if (line == null) break;
                executer.setMessage(update.getMessage().getChatId(), line);
                if (update.hasMessage()) {
                    line = reader.readLine();
                    if (update.getMessage().getText().toLowerCase().equals(line.toLowerCase())) {
                        executer.setMessage(update.getMessage().getChatId(),
                                "Правильный ответ!");
                    } else {
                        executer.setMessage(update.getMessage().getChatId(),
                                "Вы ошиблись");
                    }
                }
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
