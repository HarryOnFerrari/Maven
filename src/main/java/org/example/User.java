package org.example;

import org.example.data.Attempt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
    /** Поле названия текущего учебного предмета */
    private String currentSubject;
    /** Список всех результатов по предметам */
    private Map<String, List<Attempt>> userResults;
    /** Составитель статистики пользователя */
    private IUserStatistic statistic = new UserStatistic();

    /**
     * Функция запрашивающая статистику по предмету
     * @param subject - название учебного предмета
     * @return статистика по попыткам в рамках одного учебного предмета
     */
    public String getStatistic(String subject) {
        return statistic.getSubjectStat(userResults.get(subject), subject);
    }

    /**
     * Функция запрашивающая общую статистику по всем предметам
     * @return статистика по всем предметам, включающая только последнюю попытку
     */
    public String getStatistic() {
        return statistic.getLastAttemptSubjectStat(userResults);
    }

    /**
     * Процедура определения состояния пользователя {@link User#condition}
     * @param str - состояние
     */
    public void setCondition(String str) {
        switch (str) {
            case TEST:
                testes = new Testing(true, wrongAnswersList, link);
                int attemptNumber = userResults.get(currentSubject).size() + 1;
                userResults.get(currentSubject).add(
                        new Attempt(String.valueOf(attemptNumber), testes.getAnswers()));
                break;
            case REPEAT:
                testes = new Testing(false, wrongAnswersList, link);
                str = TEST;
                break;
            case "MATHS":
            case "RUSSIAN":
            case "ENGLISH":
                currentSubject = str;
                link = subjects.get(currentSubject).getKey();
                wrongAnswersList = subjects.get(currentSubject).getValue();
                break;
            case BACK:
                str = "";
                break;
            case STOP:
                break;
        }
        condition = str;
    }

    /**
     * Конструктор - создание нового пользователя по id чата
     * @param chatId - id чата нового пользователя
     */
    public User(Long chatId){
        this.chatId = chatId;
        condition = "";
        reminder = new TimerBehavior(chatId);
        reminder.setIsAgreeReceiveNotification(true);
        userResults = new HashMap<>();
        subjects = new HashMap<>();
        for (Subjects sub: Subjects.values()) {
            subjects.put(sub.toString(),  Map.entry(
                    sub.value(),
                    new HashMap<>()
            ));
            userResults.put(sub.toString(), new LinkedList<>());
        }
    }

    /**
     * Функция сопоставления пользователей
     * @param o - другой пользователь
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

    /**
     * Функция получения значения поля {@link User#condition}
     * @return возвращает значение состояния
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Функция получения доступа к полю {@link User#reminder}
     */
    public TimerBehavior getReminder() {
        return reminder;
    }

    /**
     * Функция получения доступа к id чата пользователя {@link User#chatId}
     * @return название предмета
     */
    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chatId) { this.chatId = chatId; }

    /**
     * Функция получения доступа к полю {@link User#testes}
     */
    public Testing getTestes() {
        return testes;
    }

    /**
     * Функция получения доступа к полю {@link User#currentSubject}
     */
    public String getCurrentSubject() {
        return currentSubject;
    }

    /**
     * Функция получения доступа к полю {@link User#userResults}
     */
    public Map<String, List<Attempt>> getUserResults() {
        return userResults;
    }
}