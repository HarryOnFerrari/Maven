package org.example;

import org.example.data.Answer;
import org.example.data.Attempt;

import java.util.*;

/**
 * Класс для формирования вывода статистики прохождения тестов по предметам пользователем
 *
 * @author Бабакова Анастасия(немножко), Пономарева Дарья(множко).
 */
public class UserStatistic implements IUserStatistic {

    /**
     * Получение статистики  прохождения тестов по конкретному предмету
     * @param attempts - результаты прохождения теста по одному из предметов
     * @param subject - название предмета
     * @return
     */
    @Override
    public String getSubjectStat(List<Attempt> attempts, String subject) {
        if (attempts.size() == 0) {
            return subject + ": нет информации, пройдите тест.\n";
        }
        StringBuilder statSubject = new StringBuilder();
        int right = 0;
        int wrong = 0;
        for (Attempt attempt : attempts) {
            for (Answer answer : attempt.getAnswers()) {
                if (answer.isCorrect()) {
                    right++;
                } else {
                    wrong++;
                }
            }
            statSubject.append(subject)
                    .append(": попытка №")
                    .append(attempt.getAttempt())
                    .append(": ");
            if (right == 0 && wrong == 0)
                statSubject.append("Вы не дали ни одного ответа\n");
            else
                statSubject.append(right).append(" - правильных, ")
                        .append(wrong).append(" - неправильных\n");
            right = 0;
            wrong = 0;
        }
        return statSubject.toString();
    }


    /**
     * Получение статистики по последней попытке каждого предмета
     * @param allResults - список с попытками всех предметов
     * @return
     */
    @Override
    public String getLastAttemptSubjectStat(Map<String, List<Attempt>> allResults) {
        StringBuilder statAllSubject = new StringBuilder();
        for (String subject : allResults.keySet()) {
            if (allResults.get(subject).size() == 0) {
                statAllSubject.append(subject).append(": нет информации, пройдите тест.\n");
            } else {
                int right = 0;
                int wrong = 0;
                int countAttempt = allResults.get(subject).size();
                Attempt lastAttempt = allResults.get(subject).get(countAttempt-1);
                for (Answer answer : lastAttempt.getAnswers()) {
                    if (answer.isCorrect()) {
                        right++;
                    } else {
                        wrong++;
                    }
                }
                statAllSubject .append(subject)
                        .append(": ");
                if (right == 0 && wrong == 0)
                    statAllSubject.append("Вы не дали ни одного ответа\n");
                else
                    statAllSubject.append(right).append(" - правильных, ")
                            .append(wrong).append(" - неправильных\n");
            }
        }
        return statAllSubject.toString();
    }
}
