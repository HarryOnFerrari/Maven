package org.example;

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
     * @return
     */
    @Override
    public String getSubjectStat(List<Attempt> attempts) {
        return null;
    }

    /**
     * Получение статистики по последней попытке каждого предмета
     * @param allResults - список с попытками всех предметов
     * @return
     */
    @Override
    public String getLastAttemptSubjectStat(Map<String, List<Attempt>> allResults) {
        return null;
    }
}
