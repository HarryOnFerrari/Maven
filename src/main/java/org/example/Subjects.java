package org.example;

public enum Subjects {
    MATHS ("Карточки математика ЕГЭ квадраты _ Quizlet.html"),
    ENGLISH ("Карточки английский _ Quizlet.html"),
    RUSSIAN ("Карточки Егэ русский язык _ Quizlet.html");

    String currentSubject;

    Subjects(String s) {
        currentSubject = s;
    }

    public String value(){
        return currentSubject;
    }
}
