package org.example;

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
            case ("/start"):
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

    public abstract void setMessage(Long id, String message);

    public abstract void setMessage(Long id, String message, String flag);
}
