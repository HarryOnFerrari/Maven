package org.example;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс Бота для консоли.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class ConsoleBot {
    private Scanner console;
    private PrintStream printer;
    public Behavior b = new Behavior() {

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

        /**
         * Функция для отправки сообщений пользователю.
         *
         * @see IBot#setMessageWithButtons(Long, String, String)
         * @param id - id чата, в который требуется отправить сообщение
         * @param message - текст сообщения
         * @param keyboardLayout - не используется
         */
        @Override
        public void setMessageWithButtons(Long id, String message, String keyboardLayout) {
            printer.println(message);
        }
    };
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
            b.readCommands(user, console.next());
        }
    }
}
