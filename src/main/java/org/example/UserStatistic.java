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

    public Integer getNumberOfTrial() {
        return numberOfTrial;
    }

    public void setNumberOfTrial(Integer numberOfTrial) {
        this.numberOfTrial = numberOfTrial;
    }
    /** Поле названия предмета */
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    /** Конструктор класса */
    public UserStatistic(Long chatId){
        this.userId = userId;
    }

    public String makeStatSubject () {
        return "";
    }

    public String makeStatGeneral() {
        String result = subject + ": " + countRightAnswer + " - правильных, " + countWrongAnswer + " - неправильных, ";
        return result;
    }
}
