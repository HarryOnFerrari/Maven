package Telegram;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

import utils.FileHTMLUtils;

/**
 * Класс Бота, который формирует тесты для пользователя.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Testing {

    /** Поле очередь */
    private LinkedList<String> listQuestions;
    /** Поле вопросов, на которые пользователь ответил неправильно */
    private HashMap<String, String> wrongUsersList;
    /** Поле размер очереди */
    private Integer size = 1;

    /** Поле правильного ответа на текущий вопрос теста */
    private String answer;

    /** Поле последнего заданного вопроса */
    private String question;

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
    public Testing(Boolean flag, HashMap<String, String> wrongList){
        wrongUsersList = wrongList;
        if (flag){
            FileHTMLUtils fileHTMLUtils = new FileHTMLUtils();
            listQuestions = fileHTMLUtils.makeListQuestions();
            size += listQuestions.size();
            if (!wrongList.isEmpty())
                listQuestions.add("Вопросы закончились\n" +
                        "Если хотите выйти из режима теста, введите /stop\n" +
                        "Если хотите отработать вопросы с ошибкой, то введите /next");
        }
        size += (wrongList != null && !wrongList.isEmpty())? wrongList.size() : 0;
    }

    /**
     * Функция для извлечения текущего вопроса и ответа на него
     * @return возвращает новый вопрос теста
     */
    public String newLine(){
        size--;
        if (listQuestions != null && !listQuestions.isEmpty()) {
            question = "Переведите на русский: " + listQuestions.remove();
            answer = listQuestions.remove();
        }
        else if (wrongUsersList != null && !wrongUsersList.isEmpty()){
            if (size == 0){
                question = "Вопросы закончились\n" +
                        "Если хотите выйти из режима теста, введите /stop\n" +
                        "Если хотите отработать вопросы с ошибкой, то введите /next";
                size = wrongUsersList.size() + 1;
            }
            else {
                question = wrongUsersList.keySet().iterator().next();
                answer = wrongUsersList.get(question);
                wrongUsersList.remove(question);
            }
        }
        else
            question = "Вопросов нет.";
        return  question;
    }

    /**
     * Функция для сохранения вопроса и ответа на него
     */
    public void saveQuestion(){
        wrongUsersList.put(question, answer);
    }
}
