package org.example;

import org.example.utils.Reminder;
import org.example.utils.UpdateTimeNotification;

import java.util.Date;
import java.util.Timer;

/**
 * Класс бота для настройки отправки уведомлений.
 * @author Бабакова Анастасия, Пономарева Дарья.
 */
public class TimerBehavior {
    /** Поле с таймером для отправки напоминаний */
    private Timer reminder;
    /** Поле, обозначающее согласие или отказ пользователя получать уведомление */
    private Boolean isAgreeReceiveNotification;
    public Boolean getAgreeReceiveNotification() {
        return isAgreeReceiveNotification;
    }

    public void setAgreeReceiveNotification(Boolean agreeReceiveNotification) {
        isAgreeReceiveNotification = agreeReceiveNotification;
    }

    /** Поле, обозначающее отказ пользователя получать уведомление на выбранный период*/
    private int offsetReceiveNotifications;
    public int getOffsetReceiveNotifications() {
        return offsetReceiveNotifications;
    }

    public void setOffsetReceiveNotifications(int offsetReceiveNotifications) {
        this.offsetReceiveNotifications = offsetReceiveNotifications;
    }


    /** Поле id пользователя-владельца таймера */
    private Long userId;

    /** Предустановленное время для отправки сообщений */
    public static int standardDispatchTime = 86400000;

    /** Конструктор класса */
    public TimerBehavior(Long userId){
        this.userId = userId;
    }

    /** Функция активации ожидания напоминания */
    public void setReminder(IBot bot) {
        if (reminder != null) {
            reminder.cancel();
        }
        if (isAgreeReceiveNotification) {
            reminder = new Timer();
            reminder.schedule(new Reminder(bot, userId), standardDispatchTime, standardDispatchTime);
        }
        if (offsetReceiveNotifications > 0) {
            reminder.cancel();
            UpdateTimeNotification updateTimeNotification = new UpdateTimeNotification();
            reminder = new Timer();
            Date currentDate = new Date();
            Date newDay = updateTimeNotification.timeUp(currentDate, offsetReceiveNotifications);
            reminder.schedule(new Reminder(bot, userId), newDay, standardDispatchTime);
        }
    }
}
