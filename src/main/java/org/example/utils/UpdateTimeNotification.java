package org.example.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UpdateTimeNotification {
    public Date timeUp(Date today, int sdvig) {
        Calendar gCalendar = new GregorianCalendar();
        System.out.println(gCalendar.getTime());
        gCalendar.setTime(today);
        gCalendar.add(Calendar.DAY_OF_YEAR,sdvig);
        System.out.println(gCalendar.getTime());
        return gCalendar.getTime();
    }
}
