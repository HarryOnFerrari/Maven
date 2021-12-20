package org.example.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий ответы пользователя в данной попытке
 * @author Пономарева Дарья
 */
public class Attempt {

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /** Номер попытки */
    private String attempt;

    /** Список всех ответов по данной попытке */
    private List<Answer> answers = new ArrayList<>();

    public Attempt() {

    }

    public Attempt(String attempt, List<Answer> answers) {
        this.attempt = attempt;
        this.answers = answers;
    }
}
