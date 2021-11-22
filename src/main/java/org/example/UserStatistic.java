package org.example;

import java.util.*;

/**
 * Класс бота для обработки данных статистики
 * @author Бабакова Анастасия, Пономарева Дарья.
 */
public class UserStatistic {
    /** Поле id пользователя */
    private Long userId;
    /** Поле количества верных ответов */
    public Integer countRightAnswer = 0;
    /** Поле количества неверных ответов */
    public Integer countWrongAnswer = 0;
    private User user;

    /** Общая таблица по всей статистике */
    Map<String, Map<Integer, String>> allSubjectsStat = initSubjectMap();

    /** Метод заполнения статистики учебными предметами */
    private Map<String, Map<Integer, String>> initSubjectMap() {
        Map<String, Map<Integer, String>> result = new HashMap<>();
        for (Subjects sub: Subjects.values()){
            result.put(sub.toString(), new HashMap<>());
        }
        return result;
    }

    /** Поле названия предмета */
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**  */
    public void startGenerateStat(){
        //createLastTestResult();
        countWrongAnswer = 0;
        countRightAnswer = 0;
        Map<Integer, String> sub = allSubjectsStat.get(subject);
        //sub.put(sub.size()+1, "Нет информации. Пройдите тест.");
        sub.put(sub.size()+1, new String());
    }

    /** Метод создания статистики по последнему резултату теста */
    public void createLastTestResult(){
        if (countRightAnswer != 0 || countWrongAnswer != 0){
            Map<Integer, String> previousSub = allSubjectsStat.get(this.subject);
            previousSub.put(previousSub.size(),
                    countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных\n");
            countWrongAnswer = 0;
            countRightAnswer = 0;
        }
    }


    /** Конструктор класса */
    public UserStatistic(Long chatId){
        this.userId = userId;
    }

    /**
     * Метод приведения статистики по попыткам конкретного предмета
     * @return статистика по предмету с указанием попыток
     */
    public String makeStatSubject () {
        createLastTestResult();
        if (allSubjectsStat.get(subject).isEmpty()) {
            return subject + ": Информации нет. Пройдите тест.";
        }
        return makeFormatStatistic(allSubjectsStat.get(subject));
    }

    /**
     * Метод приведения сводки попытка-результат к читабельному формату
     * @return строковый формат представления сводки
     */
    public String makeFormatStatistic(Map<Integer, String> results) {
        StringBuilder statSubject = new StringBuilder();
        for (Map.Entry entry : results.entrySet()) {
            entry.setValue(": попытка №" + entry.getKey() + ": " + entry.getValue() + "\n");
            statSubject.append(subject + entry.getValue());
        }

        /*for (Integer numberOfResult : results.keySet()) {
            statSubject.append(subject + ": попытка №" + numberOfResult + ": " + results.get(numberOfResult) + "\n");
        }*/
        return statSubject.toString();
    }

    /**
     * Метод создания общей сводки по всем предметам
     * @return статистика по предметам
     */
    public String makeStatGeneral() {
        createLastTestResult();
        StringBuilder result = new StringBuilder();
        for (String sub : allSubjectsStat.keySet()){
            result.append(sub);
            result.append(": ");
            Map<Integer, String> subStat = allSubjectsStat.get(sub);
            if (subStat.get(subStat.size()) != null) {
                result.append(subStat.get(subStat.size()));
            } else {
                result.append("нет информации, пройдите тест.\n");
            }
        }
        return result.toString();
    }
}
