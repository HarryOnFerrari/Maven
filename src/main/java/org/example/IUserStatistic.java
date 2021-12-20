package org.example;

import org.example.data.SubjectResult;

import java.util.List;

/**
 * Интерфейс для составления статистики прохождения тестов.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public interface IUserStatistic {
    /**
     * Получение статистики конкретного предмета
     * @param subjectResult - результаты прохождения теста по одному из предметов
     */
    String getSubjectStat(SubjectResult subjectResult);

    /**
     * Получение статистики по последней попытке каждого предмета
     * @param allResults - список с попытками всех предметов
     */
    String getLastAttemptSubjectStat(List<SubjectResult> allResults);
}
