package org.example.constants;

/**
 * Класс Бота, который хранит текстовые константы.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class CommandConstants {
    public static final String START = "/start";
    public static final String BACK = "/back";
    public static final String TEST = "/test";
    public static final String REPEAT = "/repeat";
    public static final String STOP = "/stop";
    public static final String NEXT = "/next";
    public static final String HELP = "/help";
    public static final String TIMER_ON = "timerOn";
    public static final String TIMER_OFF = "timerOff";
    public static final String HELP_INFO = "Це бот для обучения. " +
            "Сейчас ты можешь проходить здесь тесты и проверять свой скилл. \n" +
            "Просто используй: \n" +
            "/test - для запуска теста \n" +
            "/next - для перехода к следующему вопросу \n" +
            "/stop - для завершения работы \n" +
            "/back - для возвращения к выбору предмета \n" +
            "/repeat - для перехода в режим повторения ошибоных вопросов \n" +
            "/help - если тебе вдруг что-то стало непонятно";
    public static final String WRONG_COMMAND = "Такой команды пока не существует, или Вы допустили ошибку в написании. " +
            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.";
    public static final String WRONG_ANSWER = "Вы ошиблись, верный ответ: ";
    public static final String RIGHT_ANSWER = "Правильный ответ!";
    public static final String CHOOSE_MODE = "Выберите режим:";
    public static final String CHOOSE_SUBJECT = "Выберите предмет:";
    public static final String TEST_END = "Вопросы закончились\n" +
            "Если хотите выйти из режима теста, введите /stop\n" +
            "Если хотите отработать вопросы с ошибкой, то введите /next";
    public static final String REMINDER = "Вас давно не было видно. Хотите пройти тест?";
    public static final String TIMER_SETTING = "Хотите получать уведомления?";

}
