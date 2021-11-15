package org.example.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UpdateTimeNotification {
    public Date timeUp(Date today, int offset) {
        Calendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(today);
        gCalendar.add(Calendar.DAY_OF_YEAR, offset);
        return gCalendar.getTime();
    }
}
