package org.example.Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Класс Бота со списком его возможностей.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class Bot extends TelegramLongPollingBot {
    /** Поле текст инструкции */
    private String HELP = "Це бот для обучения. " +
            "Сейчас ты можешь проходить здесь тесты и проверять свой скилл. \n" +
            "Просто используй: \n" +
            "/test - для запуска теста \n" +
            "/next - для перехода к следующему вопросу \n" +
            "/stop - для завершения работы";

    /** Поле списка пользователей */
    private HashMap<Long, User> users = new HashMap<>();

    /**
     * Функция получения значения пользовательского имени бота.
     * @return пользовательское имя бота
     */
    @Override
    public String getBotUsername() {
        return "giveme100poinrsinbrs_bot";
    }

    /**
     * Функция получения значения токена бота.
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        String token = null;
        Properties prop = new Properties();
        try {
            prop.load(Bot.class.getClassLoader().getResourceAsStream("config.properties"));
            token = prop.getProperty("token");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return token;
    }

    /**
     * Функция, которая принимает и обрабатывает обновления состояния бота.
     * @param update - обновление состояния
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (!users.containsKey(update.getMessage().getChatId()))
                users.put(update.getMessage().getChatId(), new User(update.getMessage().getChatId()));
            User user = users.get(update.getMessage().getChatId());
            switch (update.getMessage().getText()){
                case ("/start"):
                    setMessage(user.chatId,
                            "Привет, работяга!");
                    break;
                case ("/help"):
                    setMessage(user.chatId, HELP);
                    break;
                case ("/test"):
                    user.setCondition("/test");
                    setMessage(user.chatId, user.testes.newLine());
                    break;
                case ("/repeat"):
                    user.setCondition("/repeat");
                    setMessage(user.chatId, user.testes.newLine());
                    break;
                case ("/next"):
                     if (!user.getCondition().equals("/test")){
                         setMessage(user.chatId,
                                 "Чтобы начать тестирование, отправьте /test");
                     }
                     else {
                        setMessage(user.chatId,
                                users.get(update.getMessage().getChatId()).testes.newLine());
                     }
                     break;
                case ("/stop"):
                    if (!user.getCondition().equals("/test")){
                        setMessage(user.chatId,
                                "Вы не начинали тестирование. " +
                                        "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
                    }
                    else {
                        setMessage(user.chatId, "Тест завершен");
                        user.setCondition("");
                    }
                    break;

                default:
                    checkFalseCommand(update);
                    break;
            }
        }
    }

    /**
     * Функция для проверки команд, не являющихся базовыми.
     * @param update - обновление текущего состояния бота
     */
    void checkFalseCommand(Update update){
        User user = users.get(update.getMessage().getChatId());
        if (user == null){
            setMessage(user.chatId,
                    "Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
        }
        else {
            if (update.getMessage().getText().equalsIgnoreCase(user.testes.getAnswer())){
                setMessage(user.chatId, "Правильный ответ!");
            }
            else {
                user.testes.saveQuestion();
                setMessage(user.chatId, "Вы ошиблись, верный ответ: " + user.testes.getAnswer());
            }
        }
    }

    /**
     * Функция для отправки сообщения пользователю.
     * @param chatId - id чата, в который требуется отправить сообщение
     * @param text - текст сообщения
     */
    public void setMessage(Long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
