package Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

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
    private HashMap<Long, User> users = new HashMap<>(); // поменять object

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
        return "2079838287:AAEdcvZtKJc8_zF2Ej8YJa94IDVuXAs25zY";
    }

    /**
     * Функция, которая принимает и обрабатывает обновления состояния бота.
     * @param update - обновление состояния
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            switch (update.getMessage().getText()){
                case ("/start"):
                    setMessage(update.getMessage().getChatId(),
                            "Привет, работяга!");
                    break;
                case ("/help"):
                    setMessage(update.getMessage().getChatId(), HELP);
                    break;
                case ("/test"):
                    User newUser = new User(update.getMessage().getChatId());
                    newUser.setCondition("/test");
                    // в будущем добавить проверку на наличие в мапе, пока пофиг
                    users.put(newUser.chatId, newUser);
                    setMessage(newUser.chatId, newUser.testes.newLine());
                    break;
                case ("/next"):
                     User user = users.get(update.getMessage().getChatId());
                     if (user == null || !user.getCondition().equals("/test")){
                         setMessage(update.getMessage().getChatId(),
                                 "Чтобы начать тестирование, отправьте /test");
                     }
                     else {
                        setMessage(update.getMessage().getChatId(),
                                users.get(update.getMessage().getChatId()).testes.newLine());
                     }
                     break;
                case ("/stop"):
                    user = users.get(update.getMessage().getChatId());
                    if (user == null || !user.getCondition().equals("/test")){
                        setMessage(update.getMessage().getChatId(),
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
            setMessage(update.getMessage().getChatId(),
                    "Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
        }
        else {
            if (update.getMessage().getText().equalsIgnoreCase(user.testes.getAnswer())){
                setMessage(user.chatId, "Правильный ответ!");
            }
            else {
                setMessage(user.chatId, "Вы ошиблись");
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
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
