package org.example;

import org.example.data.Attempt;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для составления статистики прохождения тестов.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public interface IUserStatistic {
    /**
     * Получение статистики конкретного предмета
     * @param attempts - результаты прохождения теста по одному из предметов
     */
    String getSubjectStat(List<Attempt> attempts);

    /**
     * Получение статистики по последней попытке каждого предмета
     * @param allResults - список с попытками всех предметов
     */
    String getLastAttemptSubjectStat(Map<String, List<Attempt>> allResults);
}
