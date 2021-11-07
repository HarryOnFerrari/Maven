package org.example;

import org.example.utils.FileResourcesUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public enum ButtonsForTelegram {

    TEST (keyboardForTestes()),
    CHOOSE (keyboardForChooseSubject()),
    MODE (keyboardForChooseMode());
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
    private static InlineKeyboardMarkup keyboardForChooseSubject(){
        FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
        List<String> listSubjects = fileResourcesUtils.makeListWords();
        InlineKeyboardMarkup keyboard =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        for (String subject : listSubjects){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(subject.split(" ")[0]);
            button.setCallbackData(subject.split(" ")[1]);
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            buttons.add(button);
            rowList.add(buttons);
        }
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    private static InlineKeyboardMarkup keyboardForChooseMode() {
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("тестирование");
        button1.setCallbackData("/test");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("повторение");
        button2.setCallbackData("/repeat");
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("вернуться к выбору предмета");
        button3.setCallbackData("/back");
        buttons1.add(button1);
        buttons1.add(button2);
        buttons2.add(button3);
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        rowList.add(buttons1);
        rowList.add(buttons2);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }
}