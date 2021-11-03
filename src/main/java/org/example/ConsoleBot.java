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
    public ConsoleBot(Scanner input, PrintStream output){
        console = input;
        console.useDelimiter("\n");
        printer = output;
    }
    /**
     * Метод запускает бота.
     */
    public void run(){
        User user = new User(666L);
        while (console.hasNext()){
            readCommands(user, console.next());
        }
    }

    /**
     * Функция для отправки сообщения пользователю.
     *
     * @see IBot#setMessage(Long, String)
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     */
    @Override
    public void setMessage(Long id, String message) {
        printer.println(message);
    }
}
