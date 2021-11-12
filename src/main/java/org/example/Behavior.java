package org.example;

import static org.example.constants.CommandConstants.*;

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
                setMessageWithButtons(user.chatId, CHOOSE_SUBJECT, "SUBJECT");
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
                    setMessageWithButtons(user.chatId, CHOOSE_MODE, "MODE");
                    user.setCondition("");
                }
                break;
            case ("ENGLISH"):
            case ("MATHS"):
            case ("RUSSIAN"):
                user.setCondition(command);
                setMessageWithButtons(user.chatId, CHOOSE_MODE, "MODE");
                break;
            case (BACK):
                user.setCondition(command);
                setMessageWithButtons(user.chatId, CHOOSE_SUBJECT, "SUBJECT");
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
        if (command.equalsIgnoreCase(TEST)) {
            setMessage(user.chatId, WRONG_COMMAND);
        } else {
            checkTestAnswer(user, command);
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
            setMessageWithButtons(user.chatId, RIGHT_ANSWER, "TEST");
        }
        else {
            user.testes.saveQuestion();
            setMessageWithButtons(user.chatId,WRONG_ANSWER + user.testes.getAnswer(), "TEST");
        }
    }

    /**
     * Функция для отправки сообщения пользователю.
     *
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     */
    public abstract void setMessage(Long id, String message);

    /**
     * Функция для отправки сообщения с кнопками пользователю.
     *
     * @param id - адрес отправки
     * @param message - сообщение
     * @param keyboardLayout - вариант шаблона клавиатуры
     */
    public abstract void setMessageWithButtons(Long id, String message, String keyboardLayout);
}
