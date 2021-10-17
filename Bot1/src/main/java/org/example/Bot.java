package org.example;

import java.io.File;
import java.util.Scanner;

public class Bot {
    public void run(){
        File file = new File("Tests.txt");
        Scanner s = new Scanner(System.in);
        String stroka = s.next();
        while (true){
            if (stroka.equals("/stop")) break;
            switch (stroka){
                case("/start"):
                    System.out.println("Hello, I am Bot, if you want to get information go to /help");
                    break;
                case("/help"):
                    System.out.println("Це бот для обучения.\n" +
                                        "Сейчас ты можешь проходить здесь тесты и проверять свой скилл. \n" +
                                        "Просто используй: \n" +
                                        "/test - для запуска теста \n" +
                                        "/stop - для завершения работы");
                    break;
                case("/test"):
                    System.out.println("Test");
                    break;
                default:
                    System.out.println("Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                    break;
            }
            stroka = s.next();
        }
    }
}
