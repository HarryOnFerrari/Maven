package org.example;

import java.util.HashMap;
import java.util.Map;

import static org.example.constants.CommandConstants.*;

/**
 * Класс бота для обозначения пользователя со свойствами <b>chatId</b>, <b>testes</b>, <b>condition</b>.
 * @author Пономарева Дарья, Бабакова Анастасия.
 */
public class User {
    /** Поле id чата пользователя */
    private Long chatId;
    /** Поле текущего теста пользователя */
    private Testing testes;
    /** Поле состояния пользователя */
    private String condition;
    /** Поле вопросов по текущему предмету, на которые пользователь ответил неправильно */
    private Map<String, String> wrongAnswersList;
    /** Поле ссылка на ресурс текущего предмета */
    private String link;
    /** Поле с парами "ссылка - список вопросов к повторению" для всех предметов */
    private Map<String, Map.Entry<String, Map<String, String>>> subjects;
    /** Поле поведения таймера напоминаний */
    private TimerBehavior reminder;
    /** Поле статистика пользователя */
    private UserStatistic statistic;
    /** Поле количества верных ответов */
    private int countRightAnswer = 0;
    /** Поле количества неверных ответов */
    private int countWrongAnswer = 0;
    /** Поле названия текущего учебного предмета */
    private String subject;

    /**
     * Процедура определения состояния пользователя {@link User#condition}
     * @param str - состояние
     */
    public void setCondition(String str) {
        switch (str) {
            case TEST:
                testes = new Testing(true, wrongAnswersList, link);
                statistic.startGenerateStat(subject);
                break;
            case REPEAT:
                testes = new Testing(false, wrongAnswersList, link);
                str = TEST;
                break;
            case "MATHS":
            case "RUSSIAN":
            case "ENGLISH":
                subject = str;
                link = subjects.get(subject).getKey();
                wrongAnswersList = subjects.get(subject).getValue();
                break;
            case BACK:
                str = "";
                break;
            case STOP:
                statistic.createLastTestResult(countRightAnswer, countWrongAnswer, subject);
                countRightAnswer = 0;
                countWrongAnswer = 0;
                break;
        }
        condition = str;
    }

    /**
     * Функция получения значения поля {@link User#condition}
     * @return возвращает значение состояния
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Конструктор - создание нового пользователя по id чата
     * @param chatId - id чата нового пользователя
     */
    public User(Long chatId){
        this.chatId = chatId;
        condition = "";
        reminder = new TimerBehavior(chatId);
        statistic = new UserStatistic();
        reminder.setIsAgreeReceiveNotification(true);
        subjects = new HashMap<>();
        for (Subjects sub: Subjects.values()) {
            subjects.put(sub.toString(),  Map.entry(
                    sub.value(),
                    new HashMap<>()
            ));
        }
    }

    /**
     * Функция получения доступа к полю {@link User#testes}
     */
    public Testing getTestes() {
        return testes;
    }

    /**
     * Функция получения доступа к полю {@link User#statistic}
     */
    public UserStatistic getStatistic() {
        return statistic;
    }

    /**
     * Функция получения доступа к полю {@link User#reminder}
     */
    public TimerBehavior getReminder() {
        return reminder;
    }

    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chatId) { this.chatId = chatId; }

    /**
     * Функция сопоставления пользователей
     * @param o - дугой пользователь
     * @return true - если other является данным пользователем, в противном случае false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return chatId.equals(user.chatId);
    }

    /**
     * Функция определения хэшкода по id чата
     * @return личный хэшкод пользователя
     */
    @Override
    public int hashCode() {
        return chatId.hashCode();
    }

    public void setCountWrongAnswer(int countWrongAnswer) {
        this.countWrongAnswer += countWrongAnswer;
    }

    public void setCountRightAnswer(int countRightAnswer) {
        this.countRightAnswer += countRightAnswer;
    }

    public String getSubject() {
        return subject;
    }
}