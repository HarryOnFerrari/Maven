package org.example;

public class UserStatistic {
    /** Поле текущего теста, по которому считается предметная статистика*/
    //private final Testing tests;
    /** Поле id пользователя */
    private Long userId;
    /** Поле количества верных ответов */
    public Integer countRightAnswer = 0;
    /** Поле количества неверных ответов */
    public Integer countWrongAnswer = 0;
    private User user;
    /** Поле номера попытки */
    private Integer numberOfTrial;
    /** Конструктор класса */
    public UserStatistic(Long chatId){
        //this.tests = tests;
        this.userId = userId;
    }

    public String makeStatSubject () {
        return "";
    }

    public String makeStatGeneral(User user) {
        String subject = user.getCondition();
        String result = subject + ": " + countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных, ";
        return result;
    }
}
