package org.example;

import org.example.utils.Reminder;
import org.example.utils.UpdateTimeNotification;
import org.glassfish.grizzly.utils.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import static org.example.constants.CommandConstants.*;

/**
 * Класс бота для обозначения пользователя со свойствами <b>chatId</b>, <b>testes</b>, <b>condition</b>.
 * @author Пономарева Дарья, Бабакова Анастасия.
 */
public class User {
    /** Поле id чата пользователя */
    public Long chatId;
    /** Поле текущего теста пользователя */
    public Testing testes;
    /** Поле состояния пользователя */
    private String condition;
    /** Поле вопросов по текущему предмету, на которые пользователь ответил неправильно */
    private Map<String, String> wrongList;
    /** Поле ссылка на ресурс текущего предмета */
    private String link;
    /** Поле с парами "ссылка - список вопросов к повторению" для всех предметов */
    private Map<String, Pair<String, Map<String, String>>> subjects;
    /** Поле с таймером для отправки напоминаний */
    private Timer reminder;
    /** Поле, обозначающее согласие или отказ пользователя получать уведомление */
    public Boolean isAgreeReceiveNotification;
    /** Поле, обозначающее отказ пользователя получать уведомление на выбранный период*/
    public Integer offsetReceiveNotifications;

    /** Функция активации ожидания напоминания */

    public void setReminder(IBot bot) {
        if (reminder != null) {
            reminder.cancel();
        }
        if (isAgreeReceiveNotification) {
            reminder = new Timer();
            reminder.schedule(new Reminder(bot, chatId), 10000, 10000);
        }
        if (offsetReceiveNotifications != null) {
            reminder.cancel();
            isAgreeReceiveNotification = false;
            UpdateTimeNotification updateTimeNotification = new UpdateTimeNotification();
            reminder = new Timer();
            Date currentDate = new Date();
            Date newDay = updateTimeNotification.timeUp(currentDate, offsetReceiveNotifications);
            reminder.schedule(new Reminder(bot, chatId), newDay, 10000);
            isAgreeReceiveNotification = true;
        }
    }

    /**
     * Процедура определения состояния пользователя {@link User#condition}
     * @param str - состояние
     */
    public void setCondition(String str){
        switch (str) {
            case (TEST):
                testes = new Testing(true, wrongList, link);
                break;
            case (REPEAT):
                testes = new Testing(false, wrongList, link);
                str = TEST;
                break;
            case ("MATHS"): case ("RUSSIAN"): case("ENGLISH"):
                link = subjects.get(str).getFirst();
                wrongList = subjects.get(str).getSecond();
                break;
            case (BACK):
                str = "";
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
        isAgreeReceiveNotification = true;
        subjects = new HashMap<>();
        for (Subjects sub: Subjects.values()) {
            subjects.put(sub.toString(), new Pair<>(
                    sub.value(),
                    new HashMap<>()
            ));
        }
    }

    /**
     * Функция определения хэшкода по id чата
     * @return личный хэшкод пользователя
     */
    @Override
    public int hashCode(){
        return chatId.hashCode();
    }

    /**
     * Функция сопоставления пользователей
     * @param other - id чата дугого пользователя
     * @return true - если other является данным пользователем, в противном случае false
     */
    @Override
    public boolean equals(Object other){
        if (other == null || other.getClass() != Long.class)
            return false;
        Long otherUser = (Long) other;
        return otherUser.hashCode() == this.hashCode(); // Ввести толковую проверку!!!
    }
}