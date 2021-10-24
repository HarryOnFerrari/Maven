package org.example.Telegram;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.telegram.telegrambots.meta.api.objects.Update;
import utils.FileResourcesUtils;

public class Testing {

    private Queue<String> queue;
    private Integer size = 0;

    public Integer getSize(){
        return size;
    }

    public Testing(){
        try {
            FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
            queue = fileResourcesUtils.read_files();
        } catch (IOException e) {
            e.printStackTrace();
        }
        queue.add("Вопросов больше нет");
    }

    public String newLine(){
        size--;
        return queue.poll();
    }
}
