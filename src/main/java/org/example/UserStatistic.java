package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        createLastTestResult();
        this.subject = subject;
        Map<Integer, String> sub = allSubjectsStat.get(subject);
        sub.put(sub.size()+1, "Нет информации. Пройдите тест.");
    }

    private void createLastTestResult(){
        if (countRightAnswer != 0 || countWrongAnswer != 0){
            Map<Integer, String> previousSub = allSubjectsStat.get(this.subject);
            previousSub.put(previousSub.size(),
                    countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных\n");
        }
    }


    /** Конструктор класса */
    public UserStatistic(Long chatId){
        this.userId = userId;
    }

    public String makeStatSubject () {
        createLastTestResult();
        return subject + ": " + allSubjectsStat.get(subject).toString();
    }

    public String makeStatGeneral() {
        createLastTestResult();
        StringBuilder result = new StringBuilder();
        for (String sub : allSubjectsStat.keySet()){
            result.append(sub);
            result.append(": ");
            Map<Integer, String> subStat = allSubjectsStat.get(sub);
            result.append(subStat.get(subStat.size()));
            //result.append("\n");
        }
        //String result = subject + ": " + countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных, ";
        //return result;
        return result.toString();
    }
}
