package org.example;

import org.example.utils.FileResourcesUtils;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Класс Бота, который создает тесты для пользователя.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Testing {
    /**
     * Метод проверяет, является ли строка пустой.
     * @param s Это входящая строка.
     * @return истину, если строка пустая и наоборот.
     */
    public boolean checkNull(String s){
        return s == null;
    }

    /**
     * Метод читает вопрос из файла Tests.txt, запрашивает ответ пользователя и сравнивает его с верным ответом.
     * В случае совпадения, оценивает ответ пользователя как верный и наоборот.
     *
     * По команде /stop или если в файле Tests.txt закончились вопросы возвращается в класс Bot
     *
     * @exception FileNotFoundException
     * @exception IOException
     */
    public void makeTest() {
        try {
            FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
            Queue queue = fileResourcesUtils.read_files();
            Iterator iterator = queue.iterator();
            String line;
            Scanner console = new Scanner(System.in);
            String commandLine ="";
            while (iterator.hasNext()){
                if (commandLine.equals("/stop")) {
                    System.out.println("Вы вышли из режима /test");
                    break;
                }
                line = iterator.next().toString();
                if (line == null) break;
                System.out.println(line);
                commandLine = console.next();
                if (commandLine.equalsIgnoreCase(iterator.next().toString())) {
                    System.out.println("Верно!!!");
                    if (!iterator.hasNext()){
                        System.out.println("Вопросов больше нет");
                        break;
                    }
                }
                else {
                    System.out.println("Ошибка");
                    if (!iterator.hasNext()){
                        System.out.println("Вопросов больше нет");
                        break;
                    }
                }
                commandLine = console.next();
                if (line != null & commandLine.equals("/next"))
                    continue;
                else
                    System.out.println("Введена неверная команда");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
