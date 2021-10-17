package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            switch (update.getMessage().getText()){
                case ("/start"):
                    setMessage(update.getMessage().getChatId(),
                            "Привет, работяга!");
                    break;

                case ("/test"):
                    Test.makeTest(update);
                    break;

                case ("/help"):
                    setMessage(update.getMessage().getChatId(), HELP);
                    break;

                case ("/stop"):
                    setMessage(update.getMessage().getChatId(),
                            "Эта функция будет реализована в будущих версиях :)))");
                    break;

                default:
                    setMessage(update.getMessage().getChatId(),
                            "Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                    break;
            }
        }
    }

    public void setMessage(Long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
