package org.example;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * enum с сылками на каждый предмет
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public enum Subjects {
    MATHS ("Карточки математика ЕГЭ квадраты _ Quizlet.html"),
    ENGLISH ("Карточки английский _ Quizlet.html"),
    RUSSIAN ("Карточки русский язык егэ словарные слова _ Quizlet.html");

    /** Поле доступ к ресурсу предмета из enum */
    String currentSubject;

    /** Поле список заданий для каждого предмета */
    private static Map<Subjects, String> keys = Map.of(
            MATHS, "Вычислите степень: ",
            ENGLISH, "Переведите на русский: ",
            RUSSIAN, "Введите пропущенную букву: "
    );

    /**
     * Функция доступ к ресурсу предмета из enum.
     */
    Subjects(String s) {
        currentSubject = s;
    }

    /**
     * Функция для получения ресурса предмета
     * @return ссылка на ресурс
     */
    public String value(){
        return currentSubject;
    }

    /**
     * Функция для нахождения задания для каждого предмета.
     *
     * @see Testing#key
     * @param link - ссылка на ресурс предмета
     * @return задание для предмета
     */
    public static String getKey(String link){
        for (Subjects sub: Subjects.values())
            if (sub.currentSubject.equals(link)){
                return keys.get(sub);
            }

        throw new NoSuchElementException("Нет предмета со ссылкой " + link);
    }
}
