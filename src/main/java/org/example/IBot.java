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
    void checkFalseCommand(User user, String command);

    /**
     * Функция для проверки ответов пользователя.
     *
     * @param user - пользователь
     * @param command - сообщение от пользователя
     */
    void checkTestAnswer(User user, String command);

    /**
     * Функция для обработки сообщений пользователя.
     *
     * @param user - текущий пользователь
     * @param command - сообщение пользователя
     */
    void readCommands(User user, String command);

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
     * @param keyboardLayout - вариант шаблона клавиатуры
     */
    void setMessageWithButtons(Long id, String message, String keyboardLayout);
}
