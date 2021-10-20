package org.example;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Testing {

    private Queue<String> queue;
    private Integer size = 0;

    public Integer getSize(){
        return size;
    }

    public Testing(){
        queue = new LinkedList<>();
        try {
            File file = new File("Tests.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line;
            while(true){
                size++;
                line = reader.readLine();
                if (line == null) {
                    queue.add("Вы ответили на все вопросы. Тест завершен.");
                    break;
                }
                queue.add(line);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String newLine(){
        size--;
        return queue.poll();
    }
}
