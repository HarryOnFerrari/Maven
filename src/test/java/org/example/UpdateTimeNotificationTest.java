package org.example;

import org.example.utils.UpdateTimeNotification;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Тесты на класс {@link UpdateTimeNotification}
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class UpdateTimeNotificationTest {
    /**
     * Тест на корректный перенос даты
     */
    @Test
    public void testOffsetDate()
    {
        UpdateTimeNotification updateTimeNotification = new UpdateTimeNotification();
        Date currentDate = new Date(1639267235000L); //Sun Dec 12 05:00:35 YEKT 2021
        Assert.assertEquals("Mon Dec 13 05:00:35 YEKT 2021",
                updateTimeNotification.timeUp(currentDate, 1).toString());
        Assert.assertEquals("Tue Dec 14 05:00:35 YEKT 2021",
                updateTimeNotification.timeUp(currentDate, 2).toString());
    }
}
