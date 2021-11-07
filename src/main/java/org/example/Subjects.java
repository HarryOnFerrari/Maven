package org.example;

import java.util.Map;
import java.util.NoSuchElementException;

public enum Subjects {
    MATHS ("Карточки математика ЕГЭ квадраты _ Quizlet.html"),
    ENGLISH ("Карточки английский _ Quizlet.html"),
    RUSSIAN ("Карточки Егэ русский язык _ Quizlet.html");

    String currentSubject;

    private static Map<Subjects, String> keys = Map.of(
            MATHS, "Вычислите степень: ",
            ENGLISH, "Переведите на русский: ",
            RUSSIAN, "Поставьте ударение: "
    );

    Subjects(String s) {
        currentSubject = s;
    }

    public String value(){
        return currentSubject;
    }

    public static String getKey(String link){
        for (Subjects sub: Subjects.values())
            if (sub.currentSubject.equals(link)){
                return keys.get(sub);
            }

        throw new NoSuchElementException("Нет предмета со ссылкой " + link);
    }
}
