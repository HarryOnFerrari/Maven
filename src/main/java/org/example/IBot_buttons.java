package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс Бота со списком его возможностей.
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public interface IBot {
    /** Поле текст инструкции */
    String HELP = "Це бот для обучения. " +
            "Сейчас ты можешь проходить здесь тесты и проверять свой скилл. \n" +
            "Просто используй: \n" +
            "/test - для запуска теста \n" +
            "/next - для перехода к следующему вопросу \n" +
            "/stop - для завершения работы";

    /**
     * Функция для обработки сообщений пользователя
     *
     * @param user - текущий пользователь
     * @param command - сообщение пользователя
     */
    default void readCommands(User user, String command){
        switch (command){
            case ("/start"):
                setMessage(user.chatId,
                        "Привет, работяга!");
                sendInlineKeyBoardMessage(user.chatId);
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
                    setMessage(user.chatId, user.testes.newLine());
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
            case ("ENGLISH"): case("MATHS"): case ("RUSSIAN"):
                user.setCondition(command);
                break;

            default:
                checkFalseCommand(user, command);
                break;
        }
    }

    /**
     * Функция для проверки команд, не являющихся базовыми.
     *
     * @param user - пользователь
     * @param command - сообщение от пользователя
     */
    default void checkFalseCommand(User user, String command){
        if (!user.getCondition().equals("/test")){
            setMessage(user.chatId,
                    "Такой команды пока не существует, или Вы допустили ошибку в написании. " +
                            "Воспользуйтесь командой /help, чтобы прочитать инструкцию.");
        }
        else {
            if (command.equalsIgnoreCase(user.testes.getAnswer())){
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
     *
     * @param id - id чата, в который требуется отправить сообщение
     * @param message - текст сообщения
     */
    void setMessage(Long id, String message);

    public default SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Тык");
        inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");
        inlineKeyboardButton2.setText("Тык2");
        inlineKeyboardButton2.setCallbackData("Button \"Тык2\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        //keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Fi4a").setCallbackData("CallFi4a"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        //setMessage(user.chatId, "Пример").setReplyMarkup(inlineKeyboardMarkup);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Example");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
