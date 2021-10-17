package org.example;

import java.io.*;
import java.util.Scanner;

public class Testing {
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
            while (true){
                if (stroka.equals("/stop")) break;
                try {
                    line = reader.readLine();
                    if (line == null) break;
                    System.out.println(line);
                    stroka = s.next();
                    if (stroka.equals(reader.readLine()))
                        System.out.println("Верно!!!");
                    else
                        System.out.println("Ошибка");
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
