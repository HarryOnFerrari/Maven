package Telegram;

import java.io.*;
import java.util.LinkedList;
import utils.FileResourcesUtils;

/**
 * Класс Бота, который формирует тесты для пользователя.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Testing {

    /** Поле очередь */
    private LinkedList<String> listQuestions;
    /** Поле размер очереди */
    private Integer size = 0;

    /** Поле правильного ответа на текущий вопрос теста */
    private String answer;

    /**
     * Функция получения значения поля {@link Testing#size}
     * @return возвращает текущий размер очереди вопросов
     */
    public Integer getSize(){
        return size;
    }

    /**
     * Функция получения значения поля {@link Testing#answer}
     * @return возвращает ответ на текущий вопрос теста
     */
    public String getAnswer(){
        return answer;
    }

    /**
     * Конструктор - создание нового теста
     * @exception IOException
     */
    public Testing(){
        try {
            FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
            listQuestions = fileResourcesUtils.readFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listQuestions.add("Вопросов больше нет");
        size = listQuestions.size();
    }

    /**
     * Функция для извлечения текущего вопроса и ответа на него
     * @return возвращает новый вопрос теста
     */
    public String newLine(){
        size--;
        String question = listQuestions.poll();
        answer = listQuestions.poll();
        return  question;
    }
}
