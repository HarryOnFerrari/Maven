package org.example.utils;

import org.example.IBot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Класс для создания задержки таймера по дням
 * @see org.example.User#setReminder(IBot) 
 * @author Пономарева Дарья
 */
public class UpdateTimeNotification {
    /**
     * Функция получения сдвига таймера
     * @param today день изменения настройки уведомлений пользователем
     * @param offset сдвиг на нужное количество дней
     * @return сдвиг таймера Data
     */
    public Date timeUp(Date today, int offset) {
        Calendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(today);
        gCalendar.add(Calendar.DAY_OF_YEAR, offset);
        return gCalendar.getTime();
    }
}
