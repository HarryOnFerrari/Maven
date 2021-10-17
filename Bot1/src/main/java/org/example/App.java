package org.example;

/**
 * Запуск бота.
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class App 
{
    /**
     * Основной метод запуска.
     * @see Bot#run()
     * @params args Параметры командной строки
     */
    public static void main(String[] args)
    {
        Bot bot = new Bot();
        bot.run();
    }
}
