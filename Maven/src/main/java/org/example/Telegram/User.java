package org.example.Telegram;

public class User {

    public Long chatId;
    public Testing testes;
    // добавится че-нить еще
    private String condition;

    public void setCondition(String str){
        condition = str;
    }

    public String getCondition() {
        if (condition.equals("/test") && testes.getSize()==0){
            condition = "";
        }
        return condition;
    }

    public User(Long chatId){
        this.chatId = chatId;
        testes = new Testing();
    }

    @Override
    public int hashCode(){
        return chatId.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if (other == null || other.getClass() != Long.class)
            return false;
        Long otherUser = (Long) other;
        return otherUser.hashCode() == this.hashCode(); // Ввести толковую проверку!!!
    }
}
