package org.example;

import java.util.*;

/**
 * Класс бота для обработки данных статистики, переданных пользователем
 * Данные: предмет и кол-во верных/неверных ответов по тесту этого предмета
 * Обработка: заполнение попредметной и сведение общей статистики
 *
 * @author Бабакова Анастасия(немножко), Пономарева Дарья(множко).
 */
public class UserStatistic implements IUserStatistic {
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
        Map<Integer, String> sub = allSubjectsStat.get(subject);
        sub.put(sub.size()+1, new String());
    }

    /** Метод создания статистики по последнему результату теста */
    @Override
    public void createLastTestResult(int countRightAnswer, int countWrongAnswer, String subject){
        if (countRightAnswer != 0 || countWrongAnswer != 0){
            Map<Integer, String> previousSub = allSubjectsStat.get(subject);
            previousSub.put(previousSub.size(),
                    countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных\n");
        }
    }

    /**
     * Метод приведения статистики по попыткам конкретного предмета
     * @return статистика по предмету с указанием попыток
     */
    @Override
    public String makeStatSubject (String subject) {
        if (allSubjectsStat.get(subject).isEmpty()) {
            return subject + ": Информации нет. Пройдите тест.";
        }
        return makeFormatStatistic(allSubjectsStat.get(subject), subject);
    }

    /**
     * Метод приведения сводки предмет-попытка-результат к читабельному формату
     * @return строковый формат представления сводки
     */
    private String makeFormatStatistic(Map<Integer, String> results, String subject) {
        StringBuilder statSubject = new StringBuilder();
        for (Map.Entry<Integer,String> entry : results.entrySet()) {
            if (entry.getValue().isEmpty())
                entry.setValue("Вы не дали ни одного ответа\n");
            statSubject.append(subject)
                    .append(": попытка №")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }
        return statSubject.toString();
    }

    /**
     * Метод создания общей сводки по всем предметам
     * @return статистика по предметам
     */
    @Override
    public String makeStatGeneral() {
        StringBuilder result = new StringBuilder();
        for (String sub : allSubjectsStat.keySet()){
            result.append(sub)
                  .append(": ");
            Map<Integer, String> subStat = allSubjectsStat.get(sub);
            if (subStat.get(subStat.size()) != null) {
                if (subStat.get(subStat.size()).isEmpty())
                    result.append("Вы не дали ни одного ответа\n");
                else
                    result.append(subStat.get(subStat.size()));
            } else {
                result.append("нет информации, пройдите тест.\n");
            }
        }
        return result.toString();
    }
}
