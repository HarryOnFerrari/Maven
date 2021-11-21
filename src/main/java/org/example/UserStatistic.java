package org.example;

import java.util.*;

public class UserStatistic {
    /** Поле текущего теста, по которому считается предметная статистика*/
    //private final Testing tests;
    /** Поле id пользователя */
    private Long userId;
    /** Поле количества верных ответов */
    public Integer countRightAnswer = 0;
    /** Поле количества неверных ответов */
    public Integer countWrongAnswer = 0;
    private User user;
    /** Поле номера попытки */
    private Integer numberOfTrial;

    /** hello bro how are you */
    Map<String, Map<Integer, String>> allSubjectsStat = initSubjectMap();

    private Map<String, Map<Integer, String>> initSubjectMap(){
        Map<String, Map<Integer, String>> result = new HashMap<>();
        for (Subjects sub: Subjects.values()){
            result.put(sub.toString(), new HashMap<>());
        }
        return result;
    }

    public Integer getNumberOfTrial() {
        return numberOfTrial;
    }

    public void setNumberOfTrial(Integer numberOfTrial) {
        this.numberOfTrial = numberOfTrial;
    }
    /** Поле названия предмета */
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void startGenerateStat(){
        //createLastTestResult();
        countWrongAnswer = 0;
        countRightAnswer = 0;
        Map<Integer, String> sub = allSubjectsStat.get(subject);
        //sub.put(sub.size()+1, "Нет информации. Пройдите тест.");
        sub.put(sub.size()+1, new String());
    }

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

    public String makeStatSubject () {
        createLastTestResult();
        if (allSubjectsStat.get(subject).isEmpty()) {
            return subject + ": Информации нет. Пройдите тест.";
        }
        return makeFormatStatistic(allSubjectsStat.get(subject));
    }

    public String makeFormatStatistic(Map<Integer, String> results) {
        StringBuilder statSubject = new StringBuilder();
        for (Integer numberOfResult : results.keySet()) {
            statSubject.append(subject + ": попытка №" + numberOfResult + ": " + results.get(numberOfResult) + "\n");
        }
        return statSubject.toString();
    }

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
            //result.append("\n");
        }
        //String result = subject + ": " + countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных, ";
        //return result;
        return result.toString();
    }
}
