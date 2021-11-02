package org.example;
import Telegram.Bot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class BotTest {
    private Bot bot;
    private Long chatId = 138239293L;

    @BeforeEach
    public void init(){
        bot = Mockito.spy(new Bot());
    }

    @Test
    public void checkMessage(){
        Update startUpdate = new Update();
        Message startMessage = Mockito.mock(Message.class);
        Mockito.when(startMessage.getChatId()).thenReturn(chatId);
        Mockito.when(startMessage.getText()).thenReturn("/test");
        startUpdate.setMessage(startMessage);
        bot.onUpdateReceived(startUpdate);
    }

    @Test
    public void checkMessageAfterTest(){
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.hasText()).thenReturn(true);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn("/test").thenReturn("/next");
        update.setMessage(message);
        bot.onUpdateReceived(update);
        /**
        Mockito.when(message.getText()).thenReturn("Я уеду жить в Лондон");
        update.setMessage(message);
        bot.onUpdateReceived(update); **/ // это можно было бы использовать, чтоб заполнить мап с неправильными ответами
        for (Integer i=0; i<6; i++){
            bot.onUpdateReceived(update);
        }
        Mockito.verify(bot).setMessage(chatId, "Вопросов нет."); // сама цель теста
    }
}
