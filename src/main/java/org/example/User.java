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
    private UserStatistic_new statistic;
    /** Поле с названием текущего предмета */
    private String currentSubject;

    /**
     * Процедура определения состояния пользователя {@link User#condition}
     * @param str - состояние
     */
    public void setCondition(String str) {
        switch (str) {
            case TEST:
                testes = new Testing(true, wrongAnswersList, link);
                statistic.startGenerateStat(currentSubject);
                break;
            case REPEAT:
                testes = new Testing(false, wrongAnswersList, link);
                str = TEST;
                break;
            case "MATHS":
            case "RUSSIAN":
            case "ENGLISH":
                currentSubject = str;
                link = subjects.get(str).getKey();
                wrongAnswersList = subjects.get(str).getValue();
                break;
            case BACK:
                str = "";
                break;
            case STOP:
                statistic.createLastTestResult(currentSubject);
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
        statistic = new UserStatistic_new();
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
     * Функция получения общей статистики по предметам
     */
    public String generalStatistic() {
        statistic.createLastTestResult(currentSubject);
        return statistic.makeStatGeneral();
    }

    /**
     * Функция для сохранения результата после ответа на вопрос
     */
    public void isThisRightAnswer(Boolean yes){ //rename me pls :^(
        if (yes)
            statistic.addCountRightAnswer();
        else
            statistic.addCountWrongAnswer();
    }

    /**
     *Функция получения статистики по отдельному предмету
     */
    public String subjectStatistic() {
        return statistic.makeStatSubject(currentSubject);
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
}