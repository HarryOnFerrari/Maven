package Telegram;

/**
 * Класс бота для обозначения пользователя со свойствами <b>chatId</b>, <b>testes</b>, <b>condition</b>.
 * @author Пономарева Дарья, Бабакова Анастасия.
 */
public class User {
    /** Поле id чата пользователя */
    public Long chatId;
    /** Поле текущего теста пользователя */
    public Testing testes;
    // добавится че-нить еще
    /** Поле состояния пользователя */
    private String condition;

    /**
     * Процедура определения состояния пользователя {@link User#condition}
     * @param str - состояние
     */
    public void setCondition(String str){
        condition = str;
    }

    /**
     * Функция получения значения поля {@link User#condition}
     * @return возвращает значение состояния
     */
    public String getCondition() {
        if (condition.equals("/test") && testes.getSize()==0){
            condition = "";
        }
        return condition;
    }

    /**
     * Конструктор - создание нового пользователя по id чата
     * @param chatId - id чата нового пользователя
     */
    public User(Long chatId){
        this.chatId = chatId;
        testes = new Testing();
    }

    /**
     * Функция определения хэшкода по id чата
     * @return личный хэшкод пользователя
     */
    @Override
    public int hashCode(){
        return chatId.hashCode();
    }

    /**
     * Функция сопоставления пользователей
     * @param other - id чата дугого пользователя
     * @return true - если other является данным пользователем, в противном случае false
     */
    @Override
    public boolean equals(Object other){
        if (other == null || other.getClass() != Long.class)
            return false;
        Long otherUser = (Long) other;
        return otherUser.hashCode() == this.hashCode(); // Ввести толковую проверку!!!
    }
}
