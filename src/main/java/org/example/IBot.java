package org.example;

/**
 * Интерфейс Бота со списком его возможностей.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public interface IBot {
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
                setMessage(user.chatId, "Правильный ответ!", "TEST");
            }
            else {
                user.testes.saveQuestion();
                setMessage(user.chatId,
                        "Вы ошиблись, верный ответ: " + user.testes.getAnswer(), "TEST");
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

    /**
     * Функция для отправки сообщения с кнопками пользователю.
     *
     * @param id - адрес отправки
     * @param message - сообщение
     * @param flag - вариант шаблона клавиатуры
     */
    void setMessage(Long id, String message, String flag);
}
