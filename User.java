package org.example;

import org.glassfish.grizzly.utils.Pair;

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
    private Map<String, String> wrongList;
    /** Поле ссылка на ресурс текущего предмета */
    private String link;
    /** Поле с парами "ссылка - список вопросов к повторению" для всех предметов */
    private Map<String, Pair<String, Map<String, String>>> subjects;
    /** Поле поведения таймера напоминаний */
    private TimerBehavior reminder;
    /** Поле статистика пользователя */
    private UserStatistic statistic;

    public User() { }


    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chatId) { this.chatId = chatId; }

    /**
     * Процедура определения состояния пользователя {@link User#condition}
     * @param str - состояние
     */
    public void setCondition(String str){
        switch (str) {
            case (TEST):
                testes = new Testing(true, wrongList, link);
                statistic.startGenerateStat();
                break;
            case (REPEAT):
                testes = new Testing(false, wrongList, link);
                str = TEST;
                break;
            case ("MATHS"): case ("RUSSIAN"): case("ENGLISH"):
                link = subjects.get(str).getFirst();
                wrongList = subjects.get(str).getSecond();
                statistic.setSubject(str);
                break;
            case (BACK):
                str = "";
                break;
            case (STOP):
                statistic.createLastTestResult();
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
        reminder.setAgreeReceiveNotification(true);
        subjects = new HashMap<>();
        for (Subjects sub: Subjects.values()) {
            subjects.put(sub.toString(), new Pair<>(
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