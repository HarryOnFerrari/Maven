package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserStatistic_new implements IUserStatistic {
    /** Поле количества верных ответов */
    private int countRightAnswer = 0;
    public void addCountRightAnswer() {
        this.countRightAnswer += 1;
    }

    /** Поле количества неверных ответов */
    private int countWrongAnswer = 0;
    public void addCountWrongAnswer() {
        this.countWrongAnswer += 1;
    }

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

    /** Метод подготовки класса к генерации новой статистики по предмету */
    public void startGenerateStat(String subject){
        countWrongAnswer = 0;
        countRightAnswer = 0;
        Map<Integer, String> sub = allSubjectsStat.get(subject);
        sub.put(sub.size()+1, new String());
    }

    @Override
    public void createLastTestResult(String subject) {
        if (countRightAnswer != 0 || countWrongAnswer != 0) {
            Map<Integer, String> previousSub = allSubjectsStat.get(subject);
            previousSub.put(previousSub.size(),
                    countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных\n");
            countWrongAnswer = 0;
            countRightAnswer = 0;
        }
    }

    @Override
    public String makeStatSubject(String subject) {
        createLastTestResult(subject);
        if (allSubjectsStat.get(subject).isEmpty()) {
            return subject + ": Информации нет. Пройдите тест.";
        }
        return makeFormatStatistic(allSubjectsStat.get(subject), subject);
    }

    private String makeFormatStatistic(Map<Integer, String> results, String subject) {
        StringBuilder statSubject = new StringBuilder();
        for (Map.Entry<Integer,String> entry : results.entrySet()) {
            if (entry.getValue().isEmpty())
                entry.setValue("0 - правильных, 0 - неправильных\n");
            statSubject.append(subject).append(": попытка №").append(entry.getKey()).append(": ").append(entry.getValue());
        }
        return statSubject.toString();
    }

    @Override
    public String makeStatGeneral() {
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
