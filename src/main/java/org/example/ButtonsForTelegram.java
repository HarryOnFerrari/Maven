package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public enum ButtonsForTelegram {

    TEST (keyboardForTestes());
    // добавить остальные

    InlineKeyboardMarkup current;

    ButtonsForTelegram(InlineKeyboardMarkup currentKeyboard) {
        current = currentKeyboard;
    }

    public InlineKeyboardMarkup value(){
        return current;
    }

    private static InlineKeyboardMarkup keyboardForTestes(){
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("next");
        button1.setCallbackData("/next");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("выйти из теста");
        button2.setCallbackData("/stop");
        buttons.add(button1);
        buttons.add(button2);
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        rowList.add(buttons);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }
}
