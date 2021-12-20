package org.example;

import org.example.buttons.ButtonsForTelegram;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Фейковый бот. Его задача сохранять все сообщения, которые отправил наш класс {@link Behavior}
 *
 * @author Пыжьянов Вячеслав
 * @since 07.12.2021
 */
public class FakeBot implements IBot
{
    private final List<String> messages = new ArrayList<>();

    public List<String> getKeyboard() { return keyboard; }

    private final List<String> keyboard = new ArrayList<>();

    public List<String> getMessages()
    {
        return messages;
    }

    @Override
    public void sendMessage(Long id, String message)
    {
        messages.add(message);
    }

    @Override
    public void sendMessageWithButtons(Long id, String message, String keyboardLayout)
    {
        messages.add(message);
        StringBuilder board = new StringBuilder();
        for (List<InlineKeyboardButton> buttons : ButtonsForTelegram.valueOf(keyboardLayout).value().getKeyboard()) {
            for (InlineKeyboardButton button : buttons) {
                board.append(button.getText()).append("\n");
            }
        }
        keyboard.add(board.toString());
    }
}
