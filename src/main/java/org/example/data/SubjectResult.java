package org.example.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий список попыток по данному предмету
 */
public class SubjectResult {

    /** Название предмета */
    private String subjectName;

    /** Список всех попыток по текущему предмету */
    private List<Attempt> attempts = new ArrayList<>();

    public SubjectResult() {

    }

    public SubjectResult(String subjectName, List<Attempt> attempts) {
        this.subjectName = subjectName;
        this.attempts = attempts;
    }
}
