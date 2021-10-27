package Console;

import org.example.utils.FileResourcesUtils;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
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
            LinkedList<String> queue = fileResourcesUtils.read_files();
            String line;
            String commandLine ="/next";
            while (queue.size() != 0){
                if (commandLine.equals("/stop")) {
                    output.println("Вы вышли из режима /test");
                    break;
                }
                if (!commandLine.equals("/next")){
                    output.println("Введена неверная команда. В режиме /test доступны следующие команды:\n" +
                            "/next - перейти к следующему вопросу\n" +
                            "/stop - выйти из режима тестирования");
                    commandLine = input.next();
                    continue;
                }
                line = queue.pollFirst();
                if (line == null) break;
                output.println(line);
                commandLine = input.next();
                if (commandLine.equalsIgnoreCase(queue.pollFirst())) {
                    output.println("Верно!!!");
                    if (queue.size() == 0){
                        output.println("Вопросов больше нет");
                        break;
                    }
                }
                else {
                    output.println("Ошибка");
                    if (queue.size() == 0){
                        output.println("Вопросов больше нет");
                        break;
                    }
                }
                commandLine = input.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}