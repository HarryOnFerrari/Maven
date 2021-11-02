package org.example.Console;

import java.util.Scanner;

/**
 * Класс Бота со списком его возможностей.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class ConsoleBot {
    /**
     * Метод запускает бота.
     *
     * Прописаны действия бота при получении команд: /start, /help, /test и /stop.
     * @see Testing#makeTest()
     */
    public void run(){
        Scanner console = new Scanner(System.in);
        String commandLine = console.next();
        while (true){
            if (commandLine.equals("/stop")) break;
            switch (commandLine){
                case("/start"):
                    System.out.println("Привет, я бот Валерий, для получения всей инфы вызови /help");
                    break;
                case("/help"):
                    System.out.println("Це бот для обучения.\n" +
                                        "Сейчас ты можешь проходить здесь тесты и проверять свой скилл. \n" +
                                        "Просто используй: \n" +
                                        "/test - для запуска теста \n" +
                                        "/next - для перехода к следующему вопросу, если они еще остались \n" +
                                        "/stop - для завершения работы и выхода из теста");
                    break;
                case("/test"):
                    Testing test = new Testing();
                    test.makeTest(new Scanner(System.in), System.out);
                    break;
                default:
                    System.out.println("Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                                       "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                    break;
            }
            commandLine = console.next();
        }
    }
}
