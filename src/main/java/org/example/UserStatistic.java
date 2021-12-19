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

    /**
     * Получение статистики конкретного предмета по пользователю
     *
     * @param user - пользователь, по которому необходимо вернуть статистику
     */
    @Override
    public String getSubjectStat(User user) {
        return null;
    }

    /**
     * Получение статистики по последней попытке конкретного предмета по пользователю
     *
     * @param user - пользователь, по которому необходимо вернуть статистику
     */
    @Override
    public String getLastAttemptSubjectStat(User user) {
        return null;
    }
}
