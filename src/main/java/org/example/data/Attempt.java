package org.example.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий ответы пользователя в данной попытке
 */
public class Attempt {

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
