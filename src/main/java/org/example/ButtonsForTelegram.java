package org.example;

import org.example.utils.FileResourcesUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.example.constants.CommandConstants.*;

/**
 * Состояния бота
 */

public enum ButtonsForTelegram {
    /** Состояние тестирования */
    TEST_BOARD(keyboardForTestes()),
    /** Состояние выбора учебного предмета */
    SUBJECT_BOARD(keyboardForChooseSubject()),
    /** Состояние выбора режима */
    MODE_BOARD (keyboardForChooseMode()),
    /** Состояние выбора режима */
    SETTING_BOARD (keyboardForChooseTimerSetting()),
    /** Состояние меню */
    MENU_BOARD (keyboardForMenu());


    /** Поле текущей расстановки кнопок*/
    InlineKeyboardMarkup current;

    /** Метод инициализации current */
    ButtonsForTelegram(InlineKeyboardMarkup currentKeyboard) {
        current = currentKeyboard;
    }
     /**
      * Функци для получения ресурса раксладки кнопок
      * @return ссылка на ресурс
      */
    public InlineKeyboardMarkup value(){
        return current;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для режима тестирования и повторения
     * @return итоговая расстановка
     */
    private static InlineKeyboardMarkup keyboardForTestes(){
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("next");
        button1.setCallbackData(NEXT);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("выйти из теста");
        button2.setCallbackData(STOP);
        buttons.add(button1);
        buttons.add(button2);
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        rowList.add(buttons);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для выбора предметов
     * @return итоговая расстановка
     */
    private static InlineKeyboardMarkup keyboardForChooseSubject(){
        FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();
        List<String> listSubjects = fileResourcesUtils.makeListWords();
        InlineKeyboardMarkup keyboard =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        for (String subject : listSubjects){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(subject.split(",")[0]);
            button.setCallbackData(subject.split(",")[1]);
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            buttons.add(button);
            rowList.add(buttons);
        }
        InlineKeyboardButton buttonMenu = new InlineKeyboardButton();
        buttonMenu.setText("меню");
        buttonMenu.setCallbackData(MENU);
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(buttonMenu);
        rowList.add(buttons);
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для выбора режима
     * @return итоговая расстановка
     */
    private static InlineKeyboardMarkup keyboardForChooseMode() {
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("тестирование");
        button1.setCallbackData(TEST);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("повторение");
        button2.setCallbackData(REPEAT);
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("вернуться к выбору предмета");
        button3.setCallbackData(BACK);
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

    public static InlineKeyboardMarkup keyboardForChooseTimerSetting(){
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("да");
        button1.setCallbackData("timerOn");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button1.setText("нет");
        button1.setCallbackData("timerOff");
        buttons1.add(button1);
        buttons2.add(button2);
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        rowList.add(buttons1);
        rowList.add(buttons2);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    public static InlineKeyboardMarkup keyboardForMenu() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("настройки");
        button1.setCallbackData(SETTING);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("выбор предмета");
        button2.setCallbackData(SUBJECT);
        buttons1.add(button1);
        buttons2.add(button2);
        rowList.add(buttons1);
        rowList.add(buttons2);
        keyboard.setKeyboard(rowList);
        return keyboard;
    }
}
