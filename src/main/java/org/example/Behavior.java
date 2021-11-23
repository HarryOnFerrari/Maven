package org.example;

import static org.example.constants.CommandConstants.*;

/**
 * Класс, описывающий поведение бота.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Behavior{
    private IBot bot;
    public Behavior(IBot bot){
        this.bot = bot;
    }
    /**
     * Функция для обработки сообщений пользователя
     *
     * @param user - текущий пользователь
     * @param command - сообщение пользователя
     */
    public final void readCommands(User user, String command){
        switch (command) {
            case (START):
                bot.setMessage(user.chatId,
                        "Привет, работяга!");
                bot.setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            case(SUBJECT):
                bot.setMessageWithButtons(user.chatId, CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case(MENU):
                bot.setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            case(SETTING):
                bot.setMessageWithButtons(user.chatId, TIMER_SETTING_ON, "SETTING_BOARD_ON");
                bot.setMessageWithButtons(user.chatId, TIMER_SETTING_OFF, "SETTING_BOARD_OFF");
                break;
            case (HELP):
                bot.setMessage(user.chatId, HELP_INFO);
                break;
            case (TEST):
                user.setCondition(TEST);
                bot.setMessage(user.chatId, user.testes.newLine());
                break;
            case (REPEAT):
                user.setCondition(REPEAT);
                bot.setMessage(user.chatId, user.testes.newLine());
                break;
            case (NEXT):
                if (!user.getCondition().equals(TEST)) {
                    bot.setMessage(user.chatId,
                            "Чтобы начать тестирование, отправьте /test");
                } else {
                    bot.setMessage(user.chatId, user.testes.newLine());
                }
                break;
            case (STOP):
                if (!user.getCondition().equals(TEST)) {
                    bot.setMessage(user.chatId,
                            "Вы не начинали тестирование. " +
                                    "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                } else {
                    bot.setMessage(user.chatId, "Тест завершен");
                    bot.setMessageWithButtons(user.chatId, CHOOSE_MODE, "MODE_BOARD");
                    user.setCondition("");
                }
                break;
            case ("ENGLISH"):
            case ("MATHS"):
            case ("RUSSIAN"):
                user.setCondition(command);
                bot.setMessageWithButtons(user.chatId, CHOOSE_MODE, "MODE_BOARD");
                break;
            case (BACK):
                user.setCondition(command);
                bot.setMessageWithButtons(user.chatId, CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case (TIMER_OFF):
                user.reminder.isAgreeReceiveNotification = false;
                user.reminder.offsetReceiveNotifications = null;
                user.reminder.setReminder(bot);
                bot.setMessage(user.chatId, NOTIFICATION_OFF);
                bot.setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            case (TIMER_ON):
                user.reminder.isAgreeReceiveNotification = true;
                user.reminder.offsetReceiveNotifications = null;
                user.reminder.setReminder(bot);
                bot.setMessage(user.chatId, NOTIFICATION_ON);
                bot.setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            case (TIMER_OFF_1):
            case (TIMER_OFF_2):
            case (TIMER_OFF_3):
                user.reminder.isAgreeReceiveNotification = false;
                user.reminder.offsetReceiveNotifications = Integer.parseInt(command.substring(command.length()-1));
                user.reminder.setReminder(bot);
                bot.setMessage(user.chatId, NOTIFICATION_OFF);
                bot.setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            default:
                checkFalseCommand(user, command);
                break;
        }
    }

    /**
     * Функция для проверки команд, не являющихся базовыми.
     *
     * @param user - пользователь
     * @param command - сообщение от пользователя
     */
    public final void checkFalseCommand(User user, String command){
        if (user.getCondition().equals(TEST)) {
            checkTestAnswer(user, command);
        } else {
            bot.setMessage(user.chatId, WRONG_COMMAND);
        }
    }

    /**
     * Функция для проверки ответов пользователя.
     *
     * @param user - пользователь
     * @param command - сообщение от пользователя
     */
    public final void checkTestAnswer(User user, String command){
        if (command.equalsIgnoreCase(user.testes.getAnswer())) {
            bot.setMessageWithButtons(user.chatId, RIGHT_ANSWER, "TEST_BOARD");
        }
        else {
            user.testes.saveQuestion();
            bot.setMessageWithButtons(user.chatId,WRONG_ANSWER + user.testes.getAnswer(), "TEST_BOARD");
        }
    }

}
