package org.example;

import api.longpoll.bots.model.objects.additional.Keyboard;

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
