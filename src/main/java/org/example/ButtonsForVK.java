package org.example;

import com.vk.api.sdk.objects.messages.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.constants.CommandConstants.*;

public enum ButtonsForVK {
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

    /** Метод инициализации current */
    ButtonsForVK(Keyboard currentKeyboard) {
        current = currentKeyboard;
    }

    private static Keyboard keyboardForMenu() {
        Keyboard keyboard = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("настройки")
                .setType(TemplateActionTypeNames.OPEN_LINK)).setColor(KeyboardButtonColor.DEFAULT));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("выбор предмета")
                .setType(TemplateActionTypeNames.OPEN_LINK)).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        keyboard.setButtons(allKey);
        return keyboard;
    }

    private static Keyboard keyboardForChooseTimerSettingOff() {
        return new Keyboard();
    }

    private static Keyboard keyboardForChooseTimerSettingOn() {
        return new Keyboard();
    }

    private static Keyboard keyboardForChooseMode() {
        return new Keyboard();
    }

    private static Keyboard keyboardForChooseSubject() {
        Keyboard keyboard = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        List<KeyboardButton> line3 = new ArrayList<>();
        List<KeyboardButton> line4 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("1. Математика")
                .setType(TemplateActionTypeNames.OPEN_LINK).setLink("MATHS")).setColor(KeyboardButtonColor.DEFAULT));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("2. Английский язык")
                .setType(TemplateActionTypeNames.OPEN_LINK).setLink("ENGLISH")).setColor(KeyboardButtonColor.DEFAULT));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("3. Русский язык")
                .setType(TemplateActionTypeNames.OPEN_LINK).setLink("RUSSIAN")).setColor(KeyboardButtonColor.DEFAULT));
        line4.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Меню")
                .setType(TemplateActionTypeNames.OPEN_LINK).setLink(MENU)).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        allKey.add(line4);
        keyboard.setButtons(allKey);
        return keyboard;
    }

    private static Keyboard keyboardForTestes() {
        return new Keyboard();
    }

    /**
     * Функци для получения ресурса раксладки кнопок
     * @return ссылка на ресурс
     */
    public Keyboard value(){
        return current;
    }
    /** Поле текущей расстановки кнопок*/
    Keyboard current;
}
