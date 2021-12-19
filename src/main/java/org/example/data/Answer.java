package org.example.data;

/**
 * Класс, содержащий ответ пользователя на вопрос в текущей попытке
 */
public class Answer {

    /** Формулировка вопроса */
    private String question;

    /** Ответ пользователя на вопрос */
    private String answer;

    /** Правильно ли был дан ответ пользователем на вопрос */
    private boolean isCorrect;

    public Answer() {

    }

    public Answer(String question, String answer, boolean isCorrect) {
        this.question = question;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}
