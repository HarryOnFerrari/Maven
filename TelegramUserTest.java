package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.example.constants.CommandConstants.*;

public class TelegramUserTest {
    private User user = new User();
    private Behavior behavior = new Behavior(new TelegramBot());

    /**
     * Проверка оповещения о завершении тестирования
     *
     * @result Выводится сообщение об отсутствии вопросов к тестированию
     */
    @Test
    public void checkMessageAfterTest(){
        user.setChatId(0L);
        behavior.readCommands(user, START);
        behavior.readCommands(user, SUBJECT);
        behavior.readCommands(user, "ENGLISH");
        behavior.readCommands(user, TEST);
        for (int i=0; i<151; i++){
            //bot.onUpdateReceived(update);
            behavior.readCommands(user, NEXT);
        }
        Assert.assertSame(user.getTestes().newLine(), "Вопросов нет. \nДля продолжения отправьте /start");

        //"Вопросов нет. \nДля продолжения отправьте /start"

    }
}
