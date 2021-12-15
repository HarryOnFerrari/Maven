package org.example;

public interface IUserStatistic {
    /** Метод создания статистики по последнему резултату теста */
    void createLastTestResult(String subject);

    /**
     * Метод приведения статистики по попыткам конкретного предмета
     * @return статистика по предмету с указанием попыток
     */
    String makeStatSubject(String subject);

    /**
     * Метод создания общей сводки по всем предметам
     * @return статистика по предметам
     */
    String makeStatGeneral();
}
