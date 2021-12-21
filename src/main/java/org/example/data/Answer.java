package org.example.data;

/**
 * Класс, содержащий ответ пользователя на вопрос в текущей попытке
 * @author Пономарева Дарья, Бабакова Анастасия
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

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public boolean isCorrect() { return isCorrect; }

    public void setCorrect(boolean correct) { isCorrect = correct; }
}
