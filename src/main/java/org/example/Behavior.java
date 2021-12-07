package org.example;

import static org.example.constants.CommandConstants.*;

/**
 * Класс бота для обработки его поведения с описанием команд
 * @author Бабакова Анастасия, Пономарева Дарья.
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
    public final void processCommand(User user, String command){
        switch (command) {
            case (START):
                bot.sendMessage(user.getChatId(),
                        "Привет, работяга!");
                bot.sendMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case(SUBJECT):
                bot.sendMessageWithButtons(user.getChatId(), CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case(MENU):
                bot.sendMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case(SETTING):
                bot.sendMessageWithButtons(user.getChatId(), TIMER_SETTING_ON, "SETTING_BOARD_ON");
                bot.sendMessageWithButtons(user.getChatId(), TIMER_SETTING_OFF, "SETTING_BOARD_OFF");
                break;
            case (HELP):
                bot.sendMessage(user.getChatId(), HELP_INFO);
                break;
            case (TEST):
                user.setCondition(TEST);
                bot.sendMessage(user.getChatId(), user.getTestes().newLine());
                break;
            case (REPEAT):
                user.setCondition(REPEAT);
                bot.sendMessage(user.getChatId(), user.getTestes().newLine());
                break;
            case (NEXT):
                if (!user.getCondition().equals(TEST)) {
                    bot.sendMessage(user.getChatId(),
                            "Чтобы начать тестирование, отправьте /test");
                } else {
                    bot.sendMessage(user.getChatId(), user.getTestes().newLine());
                }
                break;
            case (STOP):
                if (!user.getCondition().equals(TEST)) {
                    bot.sendMessage(user.getChatId(),
                            "Вы не начинали тестирование. " +
                                    "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                } else {
                    bot.sendMessage(user.getChatId(), "Тест завершен");
                    bot.sendMessageWithButtons(user.getChatId(), CHOOSE_MODE, "MODE_BOARD");
                    user.setCondition(STOP);
                }
                break;
            case ("ENGLISH"):
            case ("MATHS"):
            case ("RUSSIAN"):
                user.setCondition(command);
                bot.sendMessageWithButtons(user.getChatId(), CHOOSE_MODE, "MODE_BOARD");
                break;
            case (BACK):
                user.setCondition(command);
                bot.sendMessageWithButtons(user.getChatId(), CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case (TIMER_OFF):
                user.getReminder().setIsAgreeReceiveNotification(false);
                user.getReminder().setOffsetReceiveNotifications(0);
                user.getReminder().setReminder(bot);
                bot.sendMessage(user.getChatId(), NOTIFICATION_OFF);
                bot.sendMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case (TIMER_ON):
                user.getReminder().setIsAgreeReceiveNotification(true);
                user.getReminder().setOffsetReceiveNotifications(0);
                user.getReminder().setReminder(bot);
                bot.sendMessage(user.getChatId(), NOTIFICATION_ON);
                bot.sendMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case (TIMER_OFF_1):
            case (TIMER_OFF_2):
            case (TIMER_OFF_3):
                user.getReminder().setIsAgreeReceiveNotification(false);
                user.getReminder().setOffsetReceiveNotifications(Integer.parseInt(command.substring(command.length()-1)));
                user.getReminder().setReminder(bot);
                bot.sendMessage(user.getChatId(), NOTIFICATION_OFF);
                bot.sendMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case (STATISTIC_GENERAL):
                bot.sendMessage(user.getChatId(), user.getStatistic().makeStatGeneral());
                break;
            case (STATISTIC_SUBJECT):
                bot.sendMessage(user.getChatId(), user.getStatistic().makeStatSubject());
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
    private void checkFalseCommand(User user, String command){
        if (user.getCondition().equals(TEST)) {
            checkTestAnswer(user, command);
        } else {
            bot.sendMessage(user.getChatId(), WRONG_COMMAND);
        }
    }

    /**
     * Функция для проверки ответов пользователя.
     *
     * @param user - пользователь
     * @param command - сообщение от пользователя
     */
    private void checkTestAnswer(User user, String command){
        if (command.equalsIgnoreCase(user.getTestes().getAnswer())) {
            bot.sendMessageWithButtons(user.getChatId(), RIGHT_ANSWER, "TEST_BOARD");
            user.getStatistic().setCountRightAnswer(1);
        }
        else {
            user.getTestes().saveQuestion();
            bot.sendMessageWithButtons(user.getChatId(),WRONG_ANSWER + user.getTestes().getAnswer(), "TEST_BOARD");
            user.getStatistic().setCountWrongAnswer(1);
        }
    }

}
