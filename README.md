# **Deploy**

Для релиза на [Heroku](https://dashboard.heroku.com/apps) проект содержит специфические для этой площадки файлы:

* Procfile, в котором устанавливается тип процесса (worker, web и т.п.) и команда для запуска приложения
* system.properties, в котором нужно указать версию Java, если она отлична от 8  

Порядок развёртывания на Heroku:   
Вам необходимо предварительно зарегистрироваться на Heroku, а также зарегистрировать своего бота 
через BotFather (для платформы Telegram) и поместить данные для авторизации бота в config.properties. 
Аналогично можно зарегистрировать бота для платформы VK, создав группу и добавив соответствующие данные в config.properties. 

```
heroku login
heroku create <имя приложения>
git push heroku main
heroku restart worker.1
```

В личном кабинете на Heroku проверить логи: (в правом верхнем углу) кнопка More -> View logs. Нужно убедиться,
что приложение запущено. Если всё в порядке, то осталось запустить Dyno: 
в аккаунте Heroku перейти в Resources, у нужного приложения (у нас оно всего 1) нажать на карандаш справа и сместить бегунок в нужный режим.

# **Задача 1**

Бот для прохождения тестов. Работает с консоли.
Пользователь отправляет первое сообщение командой /start. Бот приветствует пользователя и ждёт следующей команды. 
Когда поступает команда /test, то бот задаёт вопрос и ожидает ответа. Далее, когда ответ от пользователя получен, то со стороны бота приходит его оценка (верно/неверно)

*Пример:*    
User: /start  
Bot: Hello!  
User: /test  
Bot: [some question]  
User: [answer]    
Bot: Good job!   
Или   
Bot: Wrong answer.  


# **Задача 2**

Реализовать алгоритм повторения тестов. Если пользователь неверно ответил на вопрос, то этот вопрос будет задан повторно для закрепления, когда тест будет пройден до конца, либо доступен по команде /repeat 

*Пример 1:* допустим, в тесте 2 вопроса, тогда  
User: /test  
Bot: [question1]    
User: [wrong answer]    
Bot: Ошибка, верный ответ: [верный ответ]    
User: /next  
Bot: [question2]    
User: [right answer]    
Bot: Верно!  
Bot: Вопросы закончились, если хотите выйти из режима теста введите /stop, иначе если хотите отработать вопросы с ошибкой, то введите /next  
User: /next  
Bot: [question1]    

User: [right answer]    
Bot: Верно! [вопрос удаляется из списка ошибочных]    
or  
User: [wrong answer]    
Bot: Ошибка! [оставляет вопрос в списке ошибочных]    

Если пользователь после прохождения теста совершил ошибки и вышел из режима тестирования командой /stop, а позже решил отработать вопросы с ошибкой, то:  
*Пример 2:*    
User: /repeat  
Bot: [answer1]    

User: [right answer]    
Bot: Верно! [вопрос удаляется из списка ошибочных]    
or  
User: [wrong answer]    
Bot: Ошибка! [оставляет вопрос в списке ошибочных]    

Если при прохождении теста пользователем не было совершено ошибок или тест ещё не был пройден, то:  
*Пример 3:*  
User: /repeat  
Bot: Список вопросов для отработки пуст. Пройдите тест!  


# **Задача 3**  

Возможность выбора учебного предмета и добавление кнопок для более удобного управления  
*Пример 1:*    
User: /start  
Bot: Привет! Давай выберем предмет  
[Список предметов в виде кнопок]    
    1. Математика    
    2. Английский язык   
    3. Русский язык    
User: [нажимает на кнопку 1]    
Bot: Вы выбрали предмет математика, хотите начать тест (/test)?  
User: [кнопка тест]  
Bot: Алгебра: [question1]  

Если пользователь захочет поменять предмет, то можно будет это сделать при помощи /back  
*Пример 2:*   
[user пользуется функционалом бота в выбранном разделе и решил его сменить]    
User: [кнопка назад]    
Bot: [выдает сообщение и список предметов из примера 1]    


# **Задача 4**

Отправление пользователю сообщения-приглашения, если тот не пользовался функционалом бота фиксированное время с возможностью отказаться от рассылки уведомлений с помощью кнопки "Настройки"  

*Пример 1:*   
[пользователь в 23:37 совершил последнее действие и более не был активен]  
Bot: [через 24 часа] Вас давно не было видно. Хотите пройти тест? [кнопки с набором предметов и кнопка "меню"]  
  
*Пример 2:*  
User: /start  
Bot: Меню:  
[кнопки "настройки" и "выбор предмета"]  
User: [настройки]  
Bot: Хотите получать уведомления?  
[кнопки "да", "нет"]  
User: [да]  
Bot: Уведомления успешно включены [переход к меню]  

или  

User: /start  
Bot: Меню:  
[кнопки "настройки" и "выбор предмета"]  
User: [выбор предмета]  
Bot: Выберите предмет:  
[кнопки с названиями предметов и кнопка "меню"]  


# **Задача 5**
Возможность узнать статистику по тесту для каждой из попыток внутри одного предмета и общую, включающую только последнюю попытку, сводку по всем предметам.  

*Пример 1:*  
User: /start  
Bot: Меню:  
[кнопки "настройки" и "выбор предмета"]  
User: [выбор предмета]  
Bot: [кнопки из списка предметов, "меню" и "статистика"]    

*допустим, пользователь ранее проходил тесты по части предметов*

User: [статистика]  
Bot: Математика: 15 - правильных, 6 - неправильных, 25 - нерешенных вопросов  
Русский язык: нет информации. Пройдите тест.  
Английский язык: 1 - правильных, 3 - неправильных, 15 - нерешенных вопросов  

*Пример 2:*  
User: /start  
Bot: Меню:  
[кнопки "настройки" и "выбор предмета"]  
User: [выбор предмета]  
Bot: [кнопки из списка предметов (математика, русский язык итд), "меню","статистика"]  
User: [Математика]  
Bot: Выберите режим:  
[кнопки "тестирование", "повторение" ,"статистика",  
"вернуться к выбору предмета"]  
User: [статистика]  
Bot: Математика, попытка 1: 5 - правильных, 7 - неправильных, 34 - нерешенных  
Математика, попытка 2: 15 - правильных, 6 - неправильных, 25 - нерешенных вопросов  

или  

Bot: Математика: нет информации. Пройдите тест.
