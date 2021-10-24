package org.example;

/**
 * Запуск бота.
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class App 
{
    /**
     * Основной метод запуска.
     * @see ConsoleBot#run()
     * @params args Параметры командной строки
     */
    public static void main(String[] args)
    {
        ConsoleBot consoleBot = new ConsoleBot();
        consoleBot.run();
    }
}
