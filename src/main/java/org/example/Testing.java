package org.example;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;

import org.example.utils.FileHTMLUtils;

import static org.example.constants.CommandConstants.TEST_END;


/**
 * Класс Бота, который формирует тесты для пользователя.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Testing {

    /** Поле очередь вопросов и верных ответов */
    private LinkedList<String> listQuestions;
    /** Поле вопросов, на которые пользователь ответил неправильно */
    private Map<String, String> wrongUsersList;

    /** Поле правильного ответа на текущий вопрос теста */
    private String answer;

    /** Поле последнего заданного вопроса */
    private String question;

    /**
     * Функция получения значения поля {@link Testing#answer}
     * @return возвращает ответ на текущий вопрос теста
     */
    public String getAnswer(){
        return answer;
    }

    /** Поле формулировки задания */
    String key;

    /**
     * Конструктор - создание нового теста
     * @exception IOException
     */
    public Testing(Boolean isChooseModeTesting, Map<String, String> wrongList, String link){
        wrongUsersList = wrongList;
        if (isChooseModeTesting){
            FileHTMLUtils fileHTMLUtils = new FileHTMLUtils();
            fileHTMLUtils.setINPUTSTREAM(link);
            listQuestions = fileHTMLUtils.makeListQuestions();
        }
        key = Subjects.getKey(link);
    }

    /**
     * Функция для извлечения текущего вопроса и ответа на него
     * @return возвращает новый вопрос теста
     */
    public String newLine(){
        if (listQuestions != null){
            if (listQuestions.isEmpty()) {
                listQuestions = null;
                question = (!wrongUsersList.isEmpty()) ? TEST_END : newLine();
            }
            else{
                    question = key + listQuestions.remove();
                    if (listQuestions.size() != 0)
                        answer = listQuestions.remove();
                }
        }
        else if (wrongUsersList != null && wrongUsersList.size() != 0) {
            question = wrongUsersList.keySet().iterator().next();
            answer = wrongUsersList.get(question);
            wrongUsersList.remove(question);
        }
        else
            question = "Вопросов нет. \nДля продолжения отправьте /start";
        return  question;
    }

    /**
     * Функция для сохранения вопроса и ответа на него
     */
    public void saveQuestion(){
        wrongUsersList.put(question, answer);
    }
}
