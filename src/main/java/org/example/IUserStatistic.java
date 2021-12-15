package org.example;

/**
 * Интерфейс для составления статистики прохождения тестов.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public interface IUserStatistic {
    /**
     * Метод создания статистики по последнему резултату теста
     *
     * @param right - количество верных ответов
     * @param wrong - количество неверных ответов
     * @param subject - название учебного предмета
     */
    void createLastTestResult(int right, int wrong, String subject);

    /**
     * Метод приведения статистики по попыткам конкретного предмета
     *
     * @param subject - название учебного предмета
     * @return статистика по предмету с указанием попыток
     */
    String makeStatSubject(String subject);

    /**
     * Метод создания общей сводки по всем предметам
     * @return статистика по предметам
     */
    String makeStatGeneral();
}
