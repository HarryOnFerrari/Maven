package org.example;

import static org.example.constants.CommandConstants.START;

public abstract class Behavior implements IBot{
    /** Поле текст инструкции */
    String HELP = "Це бот для обучения. " +
            "Сейчас ты можешь проходить здесь тесты и проверять свой скилл. \n" +
            "Просто используй: \n" +
            "/test - для запуска теста \n" +
            "/next - для перехода к следующему вопросу \n" +
            "/stop - для завершения работы";

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
                setMessage(user.chatId, "Выберите предмет:", "CHOOSE");
                break;
            case ("/help"):
                setMessage(user.chatId, HELP);
                break;
            case ("/test"):
                user.setCondition("/test");
                setMessage(user.chatId, user.testes.newLine());
                checkTestAnswer(user, command);
                break;
            case ("/repeat"):
                user.setCondition("/repeat");
                setMessage(user.chatId, user.testes.newLine());
                break;
            case ("/next"):
                if (!user.getCondition().equals("/test")) {
                    setMessage(user.chatId,
                            "Чтобы начать тестирование, отправьте /test");
                } else {
                    setMessage(user.chatId, user.testes.newLine());
                }
                break;
            case ("/stop"):
                if (!user.getCondition().equals("/test")) {
                    setMessage(user.chatId,
                            "Вы не начинали тестирование. " +
                                    "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                } else {
                    setMessage(user.chatId, "Тест завершен");
                    setMessage(user.chatId, "Выберите режим:", "MODE");
                    user.setCondition("");
                }
                break;
            case ("ENGLISH"):
            case ("MATHS"):
            case ("RUSSIAN"):
                user.setCondition(command);
                setMessage(user.chatId, "Выберите режим:", "MODE");
                break;
            case ("/back"):
                user.setCondition(command);
                setMessage(user.chatId, "Выберите предмет:", "CHOOSE");
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
        setMessage(user.chatId,
            "Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                    "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
    }

    /**
     * Функция для проверки ответов пользователя.
     *
     * @param user - пользователь
     * @param command - сообщение от пользователя
     */
    private void checkTestAnswer(User user, String command){
        if (command.equalsIgnoreCase(user.testes.getAnswer())){
            setMessage(user.chatId, "Правильный ответ!", "TEST");
        }
        else {
            user.testes.saveQuestion();
            setMessage(user.chatId,
                    "Вы ошиблись, верный ответ: " + user.testes.getAnswer(), "TEST");
        }
    }

    public abstract void setMessage(Long id, String message);

    public abstract void setMessage(Long id, String message, String flag);
}
