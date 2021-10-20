package org.example;

import java.io.*;
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
            File file = new File("src/main/resources/Tests.txt");
            //создаем объект FileReader для объекта File
            FileReader filereader = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(filereader);
            String line;
            //Scanner console = new Scanner(System.in);
            String commandLine ="";
            while (true){
                if (commandLine.equals("/stop")) {
                    output.println("Вы вышли из режима /test");
                    break;
                }
                try {
                    line = reader.readLine();
                    if (line == null) break;
                    output.println(line);
                    commandLine = input.next();
                    if (commandLine.equalsIgnoreCase(reader.readLine())) {
                        output.println("Верно!!!");
                        if (checkNull(reader.readLine())){
                            output.println("Вопросов больше нет");
                            break;
                        }
                    }
                    else {
                        output.println("Ошибка");
                        if (checkNull(reader.readLine())){
                            output.println("Вопросов больше нет");
                            break;
                        }
                    }
                    commandLine = input.next();
                    if (line != null & commandLine.equals("/next"))
                        continue;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
