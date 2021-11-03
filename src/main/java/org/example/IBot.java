package org.example;

/**
 * Интерфейс Бота со списком его возможностей.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public interface IBot {
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
    default void readCommands(User user, String command){
        switch (command){
            case ("/start"):
                setMessage(user.chatId,
                        "Привет, работяга!");
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
                if (!user.getCondition().equals("/test")){
                    setMessage(user.chatId,
                            "Чтобы начать тестирование, отправьте /test");
                }
                else {
                    setMessage(user.chatId, user.testes.newLine());
                }
                break;
            case ("/stop"):
                if (!user.getCondition().equals("/test")){
                    setMessage(user.chatId,
                            "Вы не начинали тестирование. " +
                                    "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                }
                else {
                    setMessage(user.chatId, "Тест завершен");
                    user.setCondition("");
                }
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
    default void checkFalseCommand(User user, String command){
        if (!user.getCondition().equals("/test")){
            setMessage(user.chatId,
                    "Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
        }
        else {
            if (command.equalsIgnoreCase(user.testes.getAnswer())){
                setMessage(user.chatId, "Правильный ответ!");
            }
            else {
                user.testes.saveQuestion();
                setMessage(user.chatId, "Вы ошиблись, верный ответ: " + user.testes.getAnswer());
            }
        }
    }

    /**
     * Функция для отправки сообщения пользователю.
     *
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     */
    void setMessage(Long id, String message);
}
