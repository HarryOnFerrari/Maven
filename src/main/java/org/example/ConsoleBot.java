package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Класс Бота для консоли.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class ConsoleBot implements IBot {
    private Scanner console;
    private PrintStream printer;
    private Behavior behavior = new Behavior(this);

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
     * @param keyboardLayout - клавиатура, с которой считываются команды
     * @see IBot#setMessageWithButtons(Long, String, String)
     */
    @Override
    public void setMessageWithButtons(Long id, String message, String keyboardLayout) {
        printer.println(message);
        for (List<InlineKeyboardButton> buttons : ButtonsForTelegram.valueOf(keyboardLayout).value().getKeyboard()) {
            for (InlineKeyboardButton button : buttons) {
                printer.println(button.getText() + ": " + button.getCallbackData());
            }
        }
    }

    /**
     * Конструктор класса
     * @param input - входящая строка с клавиатуры
     * @param output - текст сообщения на выходе
     */
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
            user.getReminder().setReminder(this);
            behavior.readCommands(user, console.next());
        }
    }
}
