package org.example;

import com.vk.api.sdk.objects.messages.*;

import java.util.LinkedList;
import java.util.List;

/**
 * enum с шаблонами клавиатуры на каждое состояние для vk
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
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

    /**
     * Метод создания шаблона расстановки и функционала кнопок для меню
     * @return итоговая расстановка
     */
    private static Keyboard keyboardForMenu() {
        Keyboard keyboard = new Keyboard().setOneTime(true);

        List<List<KeyboardButton>> allKey = new LinkedList<>();
        List<KeyboardButton> line1 = new LinkedList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("настройки")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"/setting\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("выбор предмета")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"SUBJECT\"}")).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        keyboard.setButtons(allKey);
        return keyboard;
    }

    private static Keyboard keyboardForChooseTimerSettingOff() {
        return new Keyboard();
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для настроек получения уведомлений
     * @return итоговая расстановка
     */
    private static Keyboard keyboardForChooseTimerSettingOn() {
        Keyboard keyboard = new Keyboard().setOneTime(true);

        List<List<KeyboardButton>> allKey = new LinkedList<>();
        List<KeyboardButton> line1 = new LinkedList<>();
        List<KeyboardButton> line2 = new LinkedList<>();
        List<KeyboardButton> line3 = new LinkedList<>();
        List<KeyboardButton> line4 = new LinkedList<>();
        List<KeyboardButton> line5 = new LinkedList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("да, всегда")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"timerOn\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("нет, 1 день")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"timer_off_1\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("нет, 2 дня")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"timer_off_2\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line4.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("нет, 3 дня")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"timer_off_3\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line5.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("нет, никогда")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"timerOff\"}")).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        allKey.add(line4);
        allKey.add(line5);
        keyboard.setButtons(allKey);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для выбора режима внутри предмета
     * @return итоговая расстановка
     */
    private static Keyboard keyboardForChooseMode() {
        Keyboard keyboard = new Keyboard().setOneTime(true);

        List<List<KeyboardButton>> allKey = new LinkedList<>();
        List<KeyboardButton> line1 = new LinkedList<>();
        List<KeyboardButton> line2 = new LinkedList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("тестирование")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"/test\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("повторение")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"/repeat\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("вернуться к выбору предмета")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"/back\"}")).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        allKey.add(line2);
        keyboard.setButtons(allKey);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для выбора предметов
     * @return итоговая расстановка
     */
    private static Keyboard keyboardForChooseSubject() {
        Keyboard keyboard = new Keyboard().setOneTime(true);

        List<List<KeyboardButton>> allKey = new LinkedList<>();
        List<KeyboardButton> line1 = new LinkedList<>();
        List<KeyboardButton> line2 = new LinkedList<>();
        List<KeyboardButton> line3 = new LinkedList<>();
        List<KeyboardButton> line4 = new LinkedList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("1. Математика")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"MATHS\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("2. Английский язык")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"ENGLISH\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("3. Русский язык")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"RUSSIAN\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line4.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Меню")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"/menu\"}")).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        allKey.add(line4);
        keyboard.setButtons(allKey);
        return keyboard;
    }

    /**
     * Метод создания шаблона расстановки и функционала кнопок для режима тестирования и повторения
     * @return итоговая расстановка
     */
    private static Keyboard keyboardForTestes() {
        Keyboard keyboard = new Keyboard().setOneTime(true);

        List<List<KeyboardButton>> allKey = new LinkedList<>();
        List<KeyboardButton> line1 = new LinkedList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("next")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"/next\"}")).setColor(KeyboardButtonColor.DEFAULT));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("выйти из теста")
                .setType(TemplateActionTypeNames.TEXT).setPayload("{\"\":\"/stop\"}")).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        keyboard.setButtons(allKey);
        return keyboard;
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
