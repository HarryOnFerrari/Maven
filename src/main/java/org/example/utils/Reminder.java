package org.example.utils;
import org.example.*;

import java.util.Date;
import java.util.TimerTask;

import static org.example.constants.CommandConstants.REMINDER;

/**
 * Класс создания напоминания пользователю.
 * @see User#setReminder(Behavior)
 * @author Пономарева Дарья, Бабакова Анастасия.
 */
public class Reminder extends TimerTask {
    /** Функционал бота */
    Behavior bot;
    /** Поле пользователя, которому отправляется напоминание */
    Long user;

    /** Конструктор класса */
    public Reminder(Behavior bot, Long user){
        this.bot = bot;
        this.user = user;
    }

    /**
     * Функция создания задания для таймера
     * @see TimerTask#run()
     */
    @Override
    public void run() {
        bot.setMessageWithButtons(user, REMINDER, "SUBJECT_BOARD");
    }


}
