package org.example.utils;
import org.example.*;

import java.util.TimerTask;

import static org.example.constants.CommandConstants.REMINDER;

public class Reminder extends TimerTask {

    Behavior bot;
    Long user;

    public Reminder(Behavior bot, Long user){
        this.bot = bot;
        this.user = user;
    }

    @Override
    public void run() {
        bot.setMessageWithButtons(user, REMINDER, "SUBJECT");
    }
}
