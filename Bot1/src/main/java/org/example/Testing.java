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
    public void makeTest(Scanner input, PrintStream output) {
        try {
            FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
            Queue queue = fileResourcesUtils.read_files();
            Iterator iterator = queue.iterator();
            String line;
            String commandLine ="";
            while (iterator.hasNext()){
                if (commandLine.equals("/stop")) {
                    output.println("Вы вышли из режима /test");
                    break;
                }
                line = iterator.next().toString();
                if (line == null) break;
                output.println(line);
                commandLine = input.next();
                if (commandLine.equalsIgnoreCase(iterator.next().toString())) {
                    output.println("Верно!!!");
                    if (!iterator.hasNext()){
                        output.println("Вопросов больше нет");
                        break;
                    }
                }
                else {
                    output.println("Ошибка");
                    if (!iterator.hasNext()){
                        output.println("Вопросов больше нет");
                        break;
                    }
                }
                commandLine = input.next();
                if (line != null & commandLine.equals("/next"))
                    continue;
                else
                    output.println("Введена неверная команда");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
=======
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
     * Метод получает список вопросов и верных ответов в виде очереди, 
     * запрашивает ответ пользователя и сравнивает его с верным ответом.
     * В случае совпадения, оценивает ответ пользователя как верный и наоборот.
     *
     * По команде /stop или если в очереди закончились вопросы возвращается в класс Bot
     *
     * @exception FileNotFoundException
     * @exception IOException
     */
    public void makeTest(Scanner input, PrintStream output) {
        try {
            FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
            Queue queue = fileResourcesUtils.read_files();
            Iterator iterator = queue.iterator();
            String line;
            String commandLine ="";
            while (iterator.hasNext()){
                if (commandLine.equals("/stop")) {
                    output.println("Вы вышли из режима /test");
                    break;
                }
                line = iterator.next().toString();
                if (line == null) break;
                output.println(line);
                commandLine = input.next();
                if (commandLine.equalsIgnoreCase(iterator.next().toString())) {
                    output.println("Верно!!!");
                    if (!iterator.hasNext()){
                        output.println("Вопросов больше нет");
                        break;
                    }
                }
                else {
                    output.println("Ошибка");
                    if (!iterator.hasNext()){
                        output.println("Вопросов больше нет");
                        break;
                    }
                }
                commandLine = input.next();
                if (line != null & commandLine.equals("/next"))
                    continue;
                else
                    output.println("Введена неверная команда");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}