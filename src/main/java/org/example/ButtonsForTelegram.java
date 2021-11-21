package org.example;

import org.example.utils.FileResourcesUtils;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
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
    /** Состояние выбора режима внутри предмета*/
    MODE_BOARD (keyboardForChooseMode()),
    /** Состояние настроек включения уведомлений*/
    SETTING_BOARD_ON (keyboardForChooseTimerSettingOn()),
    /** Состояние настроек выключения уведомлений */
    SETTING_BOARD_OFF(keyboardForChooseTimerSettingOff()),
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
        List<InlineKeyboardButton> buttons = new LinkedList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("next");
        button1.setCallbackData(NEXT);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("выйти из теста");
        button2.setCallbackData(STOP);
        buttons.add(button1);
        buttons.add(button2);
        List<List<InlineKeyboardButton>> rowList= new LinkedList<>();
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
        List<List<InlineKeyboardButton>> rowList= new LinkedList<>();
        for (String subject : listSubjects){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(subject.split(",")[0]);
            button.setCallbackData(subject.split(",")[1]);
            List<InlineKeyboardButton> buttons = new LinkedList<>();
            buttons.add(button);
            rowList.add(buttons);
        }
        InlineKeyboardButton buttonMenu = new InlineKeyboardButton();
        InlineKeyboardButton buttonStatistic = new InlineKeyboardButton();
        buttonMenu.setText("меню");
        buttonStatistic.setText("статистика");
        buttonMenu.setCallbackData(MENU);
        buttonStatistic.setCallbackData(STATISTIC_GENERAL);
        List<InlineKeyboardButton> buttons = new LinkedList<>();
        List<InlineKeyboardButton> buttonsStatistic = new LinkedList<>();
        buttons.add(buttonMenu);
        buttonsStatistic.add(buttonStatistic);
        rowList.add(buttons);
        rowList.add(buttonsStatistic);
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для выбора режима внутри предмета
     * @return итоговая расстановка
     */
    private static InlineKeyboardMarkup keyboardForChooseMode() {
        List<InlineKeyboardButton> buttons1 = new LinkedList<>();
        List<InlineKeyboardButton> buttons2 = new LinkedList<>();
        List<InlineKeyboardButton> buttons3 = new LinkedList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("тестирование");
        button1.setCallbackData(TEST);
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("повторение");
        button2.setCallbackData(REPEAT);
        InlineKeyboardButton buttonStatistic = new InlineKeyboardButton();
        buttonStatistic.setText("статистика");
        buttonStatistic.setCallbackData(STATISTIC_SUBJECT);
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("вернуться к выбору предмета");
        button3.setCallbackData(BACK);
        buttons1.add(button1);
        buttons1.add(button2);
        buttons2.add(buttonStatistic);
        buttons3.add(button3);
        List<List<InlineKeyboardButton>> rowList= new LinkedList<>();
        rowList.add(buttons1);
        rowList.add(buttons2);
        rowList.add(buttons3);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для настроек получения уведомлений
     * @return итоговая расстановка
     */
    public static InlineKeyboardMarkup keyboardForChooseTimerSettingOn(){
        List<InlineKeyboardButton> buttons1 = new LinkedList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("включить уведомления");
        button1.setCallbackData(TIMER_ON);
        buttons1.add(button1);
        List<List<InlineKeyboardButton>> rowList= new LinkedList<>();
        rowList.add(buttons1);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для настроек отказа от получения уведомлений
     * @return итоговая расстановка
     */
    public static InlineKeyboardMarkup keyboardForChooseTimerSettingOff() {
        List<InlineKeyboardButton> buttons1 = new LinkedList<>();
        List<InlineKeyboardButton> buttons2 = new LinkedList<>();
        List<InlineKeyboardButton> buttons3 = new LinkedList<>();
        List<InlineKeyboardButton> buttonsForever = new LinkedList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        InlineKeyboardButton buttonForever = new InlineKeyboardButton();
        button1.setText("1 день");
        button1.setCallbackData("timer_off_1"); //86400000 ms
        buttons1.add(button1);
        button2.setText("2 дня");
        button2.setCallbackData("timer_off_2"); //172800000
        buttons2.add(button2);
        button3.setText("3 дня");
        button3.setCallbackData("timer_off_3"); //259200000
        buttons3.add(button3);
        buttonForever.setText("навсегда");
        buttonForever.setCallbackData(TIMER_OFF);
        buttonsForever.add(buttonForever);
        List<List<InlineKeyboardButton>> rowList= new LinkedList<>();
        rowList.add(buttons1);
        rowList.add(buttons2);
        rowList.add(buttons3);
        rowList.add(buttonsForever);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для меню
     * @return итоговая расстановка
     */
    public static InlineKeyboardMarkup keyboardForMenu() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new LinkedList<>();
        List<InlineKeyboardButton> buttons1 = new LinkedList<>();
        List<InlineKeyboardButton> buttons2 = new LinkedList<>();
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
