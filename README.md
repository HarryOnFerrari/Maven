**Задача 1**

Бот для прохождения тестов. Работает с консоли.
Пользователь отправляет первое сообщение командой /start. Бот приветствует пользователя и ждёт следующей команды. 
Когда поступает команда /test, то бот задаёт вопрос и ожидает ответа. Далее, когда ответ от пользователя получен, то со стороны бота приходит его оценка (верно/неверно)

Пример:  
User: /start  
Bot: Hello!  
User: /test  
Bot: [some question]
User: *answer*  
Bot: Good job!   
Или   
Bot: Wrong answer.  


**Задача 2**

Реализовать алгоритм повторения тестов. Если пользователь неверно ответил на вопрос, то этот вопрос будет задан повторно для закрепления, когда тест будет пройден до конца, либо доступен по команде /repeat 

Пример 1. Допустим, в тесте 2 вопроса, тогда

User: /test  
Bot: *question1*  
User: *wrong answer*  
Bot: Ошибка, верный ответ: *верный ответ*  
User: /next  
Bot: *question2*  
User: *right answer*  
Bot: Верно!  
Bot: Вопросы закончились, если хотите выйти из режима теста введите /stop, иначе если хотите отработать вопросы с ошибкой, то введите /next  
User: /next  
Bot: *question1*  

User: *right answer*  
Bot: Верно! *вопрос удаляется из списка ошибочных*  
or  
User: *wrong answer*  
Bot: Ошибка! *оставляет вопрос в списке ошибочных*  

Если пользователь после прохождения теста совершил ошибки и вышел из режима тестирования командой /stop, а позже решил отработать вопросы с ошибкой, то:  

Пример 2  
User: /repeat  
Bot: *answer1*  

User: *right answer*  
Bot: Верно! *вопрос удаляется из списка ошибочных*  
or  
User: *wrong answer*  
Bot: Ошибка! *оставляет вопрос в списке ошибочных*  

Если при прохождении теста пользователем не было совершено ошибок или тест ещё не был пройден, то:  

Пример 3  
User: /repeat  
Bot: Список вопросов для отработки пуст. Пройдите тест!  
