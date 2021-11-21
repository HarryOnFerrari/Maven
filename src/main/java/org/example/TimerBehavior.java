package org.example;

import org.example.utils.Reminder;
import org.example.utils.UpdateTimeNotification;

import java.util.Date;
import java.util.Timer;

public class TimerBehavior {
    /** Поле с таймером для отправки напоминаний */
    private Timer reminder;
    /** Поле, обозначающее согласие или отказ пользователя получать уведомление */
    public Boolean isAgreeReceiveNotification;
    /** Поле, обозначающее отказ пользователя получать уведомление на выбранный период*/
    public Integer offsetReceiveNotifications;
    /** Поле id пользователя-владельца таймера */
    private Long userId;
    /**  */
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
        if (offsetReceiveNotifications != null) {
            reminder.cancel();
            UpdateTimeNotification updateTimeNotification = new UpdateTimeNotification();
            reminder = new Timer();
            Date currentDate = new Date();
            Date newDay = updateTimeNotification.timeUp(currentDate, offsetReceiveNotifications);
            reminder.schedule(new Reminder(bot, userId), newDay, standardDispatchTime);
        }
    }
}
