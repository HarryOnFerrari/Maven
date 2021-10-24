package Telegram;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.telegram.telegrambots.meta.api.objects.Update;
import utils.FileResourcesUtils;

/**
 * Класс Бота, который формирует тесты для пользователя.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Testing {

    /** Поле очередь */
    private Queue<String> queue;
    /** Поле размер очереди */
    private Integer size = 0;

    /**
     * Функция получения значения поля {@link Testing#size}
     * @return возвращает название производителя
     */
    public Integer getSize(){
        return size;
    }

    /**
     * Конструктор - создание нового теста
     * @exception IOException
     */
    public Testing(){
        try {
            FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
            queue = fileResourcesUtils.read_files();
        } catch (IOException e) {
            e.printStackTrace();
        }
        queue.add("Вопросов больше нет");
        size = queue.size();
    }

    public String newLine(){
        size--;
        return queue.poll();
    }
}
