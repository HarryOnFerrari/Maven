package org.example;

import org.example.data.SubjectResult;

import java.util.*;

/**
 * Класс для формирования вывода статистики прохождения тестов по предметам пользователем
 *
 * @author Бабакова Анастасия(немножко), Пономарева Дарья(множко).
 */
public class UserStatistic implements IUserStatistic {

    /**
     * Получение статистики  прохождения тестов по конкретному предмету
     * @param subjectResult - результаты прохождения теста по одному из предметов
     * @return
     */
    @Override
    public String getSubjectStat(SubjectResult subjectResult) {
        return null;
    }

    /**
     * Получение статистики по последней попытке каждого предмета
     * @param allResults - список с попытками всех предметов
     * @return
     */
    @Override
    public String getLastAttemptSubjectStat(List<SubjectResult> allResults) {
        return null;
    }
}
