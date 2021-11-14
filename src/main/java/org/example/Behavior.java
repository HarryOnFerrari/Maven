package org.example;

import static org.example.constants.CommandConstants.*;

/**
 * Класс Бота, который описывает его поведение.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public abstract class Behavior implements IBot{
    /**
     * Функция для обработки сообщений пользователя
     *
     * @param user - текущий пользователь
     * @param command - сообщение пользователя
     */
    public void readCommands(User user, String command){
        switch (command) {
            case (START):
                setMessage(user.chatId,
                        "Привет, работяга!");
                setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            case(SUBJECT):
                setMessageWithButtons(user.chatId, CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case(MENU):
                setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            case(SETTING):
                setMessageWithButtons(user.chatId, TIMER_SETTING, "SETTING_BOARD");
                break;
            case (HELP):
                setMessage(user.chatId, HELP_INFO);
                break;
            case (TEST):
                user.setCondition(TEST);
                setMessage(user.chatId, user.testes.newLine());
                break;
            case (REPEAT):
                user.setCondition(REPEAT);
                setMessage(user.chatId, user.testes.newLine());
                break;
            case (NEXT):
                if (!user.getCondition().equals(TEST)) {
                    setMessage(user.chatId,
                            "Чтобы начать тестирование, отправьте /test");
                } else {
                    setMessage(user.chatId, user.testes.newLine());
                }
                break;
            case (STOP):
                if (!user.getCondition().equals(TEST)) {
                    setMessage(user.chatId,
                            "Вы не начинали тестирование. " +
                                    "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                } else {
                    setMessage(user.chatId, "Тест завершен");
                    setMessageWithButtons(user.chatId, CHOOSE_MODE, "MODE_BOARD");
                    user.setCondition("");
                }
                break;
            case ("ENGLISH"):
            case ("MATHS"):
            case ("RUSSIAN"):
                user.setCondition(command);
                setMessageWithButtons(user.chatId, CHOOSE_MODE, "MODE_BOARD");
                break;
            case (BACK):
                user.setCondition(command);
                setMessageWithButtons(user.chatId, CHOOSE_SUBJECT, "SUBJECT_BOARD");
                break;
            case (TIMER_OFF):
                user.reminderFlag = false;
                user.setReminder(this);
                setMessage(user.chatId, "Уведомления выключены");
                setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
                break;
            case (TIMER_ON):
                user.reminderFlag = true;
                user.setReminder(this);
                setMessage(user.chatId, "Уведомления успешно включены");
                setMessageWithButtons(user.chatId, MENU_MODE, "MENU_BOARD");
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
    public void checkFalseCommand(User user, String command){
        if (user.getCondition().equals(TEST)) {
            checkTestAnswer(user, command);
        } else {
            setMessage(user.chatId, WRONG_COMMAND);
        }
    }

    /**
     * Функция для проверки ответов пользователя.
     *
     * @param user - пользователь
     * @param command - сообщение от пользователя
     */
    public void checkTestAnswer(User user, String command){
        if (command.equalsIgnoreCase(user.testes.getAnswer())){
            setMessageWithButtons(user.chatId, RIGHT_ANSWER, "TEST_BOARD");
        }
        else {
            user.testes.saveQuestion();
            setMessageWithButtons(user.chatId,WRONG_ANSWER + user.testes.getAnswer(), "TEST_BOARD");
        }
    }

    /**
     * Функция для отправки сообщения пользователю.
     *
     * @see IBot#setMessage(Long, String)
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     */
    public abstract void setMessage(Long id, String message);

    /**
     * Функция для отправки сообщения с кнопками пользователю.
     *
     * @see IBot#setMessageWithButtons(Long, String, String)
     * @see ButtonsForTelegram
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     * @param keyboardLayout - вариант шаблона клавиатуры
     */
    public abstract void setMessageWithButtons(Long id, String message, String keyboardLayout);
}
