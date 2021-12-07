package org.example;

import static org.example.constants.CommandConstants.*;

/**
 * Класс бота для обработки его поведения с описанием команд
 * @author Бабакова Анастасия, Пономарева Дарья.
 */
public class Behavior{
    private final IBot bot;
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
            case START:
                bot.setMessage(user.getChatId(),
                        "Привет, работяга!");
                bot.setMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case SUBJECT:
                bot.setMessageWithButtons(user.getChatId(), CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case MENU:
                bot.setMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case SETTING:
                bot.setMessageWithButtons(user.getChatId(), TIMER_SETTING_ON, "SETTING_BOARD_ON");
                bot.setMessageWithButtons(user.getChatId(), TIMER_SETTING_OFF, "SETTING_BOARD_OFF");
                break;
            case HELP:
                bot.setMessage(user.getChatId(), HELP_INFO);
                break;
            case TEST:
                user.setCondition(TEST);
                bot.setMessage(user.getChatId(), user.getTestes().newLine());
                break;
            case REPEAT:
                user.setCondition(REPEAT);
                bot.setMessage(user.getChatId(), user.getTestes().newLine());
                break;
            case NEXT:
                if (!user.getCondition().equals(TEST)) {
                    bot.setMessage(user.getChatId(),
                            "Чтобы начать тестирование, отправьте /test");
                } else {
                    bot.setMessage(user.getChatId(), user.getTestes().newLine());
                }
                break;
            case STOP:
                if (!user.getCondition().equals(TEST)) {
                    bot.setMessage(user.getChatId(),
                            "Вы не начинали тестирование. " +
                                    "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                } else {
                    bot.setMessage(user.getChatId(), "Тест завершен");
                    bot.setMessageWithButtons(user.getChatId(), CHOOSE_MODE, "MODE_BOARD");
                    user.setCondition(STOP);
                }
                break;
            case "ENGLISH":
            case "MATHS":
            case "RUSSIAN":
                user.setCondition(command);
                bot.setMessageWithButtons(user.getChatId(), CHOOSE_MODE, "MODE_BOARD");
                break;
            case BACK:
                user.setCondition(command);
                bot.setMessageWithButtons(user.getChatId(), CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case TIMER_OFF:
                user.getReminder().setIsAgreeReceiveNotification(false);
                user.getReminder().setOffsetReceiveNotifications(0);
                user.getReminder().setReminder(bot);
                bot.setMessage(user.getChatId(), NOTIFICATION_OFF);
                bot.setMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case TIMER_ON:
                user.getReminder().setIsAgreeReceiveNotification(true);
                user.getReminder().setOffsetReceiveNotifications(0);
                user.getReminder().setReminder(bot);
                bot.setMessage(user.getChatId(), NOTIFICATION_ON);
                bot.setMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case TIMER_OFF_1:
            case TIMER_OFF_2:
            case TIMER_OFF_3:
                user.getReminder().setIsAgreeReceiveNotification(false);
                user.getReminder().setOffsetReceiveNotifications(Integer.parseInt(command.substring(command.length()-1)));
                user.getReminder().setReminder(bot);
                bot.setMessage(user.getChatId(), NOTIFICATION_OFF);
                bot.setMessageWithButtons(user.getChatId(), MENU_MODE, "MENU_BOARD");
                break;
            case STATISTIC_GENERAL:
                bot.setMessage(user.getChatId(), user.getStatistic().makeStatGeneral());
                break;
            case STATISTIC_SUBJECT:
                bot.setMessage(user.getChatId(), user.getStatistic().makeStatSubject());
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
            bot.setMessage(user.getChatId(), WRONG_COMMAND);
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
            bot.setMessageWithButtons(user.getChatId(), RIGHT_ANSWER, "TEST_BOARD");
            user.getStatistic().setCountRightAnswer(1);
        }
        else {
            user.getTestes().saveQuestion();
            bot.setMessageWithButtons(user.getChatId(),WRONG_ANSWER + user.getTestes().getAnswer(), "TEST_BOARD");
            user.getStatistic().setCountWrongAnswer(1);
        }
    }

}
