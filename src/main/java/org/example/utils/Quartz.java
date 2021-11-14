package org.example.utils;

import org.example.TelegramBot;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Quartz {
    public static void quartzRun() throws SchedulerException {
        JobDetail schebuledTelegramTask = JobBuilder.newJob(TelegramBot.class).build();
        //JobDetail schebuledTelegramTask = JobBuilder.newJob(ScheduledTasks.class).build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(schebuledTelegramTask, trigger);
        //scheduler.start();
    }
}
