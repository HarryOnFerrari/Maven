package org.example;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс Бота для консоли.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class ConsoleBot implements IBot {
    private Scanner console;
    private PrintStream printer;
    public Behavior b = new Behavior(this);

    /**
     * Функция для отправки сообщения пользователю.
     *
     * @param id      - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     * @see IBot#setMessage(Long, String)
     */
    @Override
    public void setMessage(Long id, String message) {
        printer.println(message);
    }

    /**
     * Функция для отправки сообщений пользователю.
     *
     * @param id             - id чата, в который требуется отправить сообщение
     * @param message        - текст сообщения
     * @param keyboardLayout - не используется
     * @see IBot#setMessageWithButtons(Long, String, String)
     */
    @Override
    public void setMessageWithButtons(Long id, String message, String keyboardLayout) {
        printer.println(message);
    }

    public ConsoleBot(Scanner input, PrintStream output) {
        console = input;
        console.useDelimiter("\n");
        printer = output;
    }

    /**
     * Метод запускает бота.
     */
    public void run() {
        User user = new User(666L);
        while (console.hasNext()) {
            b.readCommands(user, console.next());
        }
    }
}
