package org.example;

import java.io.*;
import java.util.Scanner;

public class Testing {
    public boolean cheakNull(String s){
        return s == null;
    }

    public void makeTest() {
        try {
            File file = new File("Tests.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line;
            Scanner s = new Scanner(System.in);
            String stroka ="";
            while (!stroka.equals("/stop")){
                try {
                    line = reader.readLine();
                    if (line == null) break;
                    System.out.println(line);
                    stroka = s.next();
                    if (stroka.equalsIgnoreCase(reader.readLine())) {
                        System.out.println("Верно!!!");
                        if (cheakNull(reader.readLine())){
                            System.out.println("Вопросов больше нет");
                            break;
                        }
                    }
                    else {
                        System.out.println("Ошибка");
                        if (cheakNull(reader.readLine())){
                            System.out.println("Вопросов больше нет");
                            break;
                        }
                    }
                    stroka = s.next();
                    if (line != null && stroka.equals("/next")) {
                        continue;
                    }
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
