package org.example;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * junit тестирование класса ConsoleBot
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class ConsoleBotTest {
    /**
     * Проверка бота на корректный выход из режима тестов.
     *
     * @result По завершению списка вопросов пользователь получает сообщение,
     *         ошибок не возникает.
     */
    @Test
    public void repeatingTests()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        String str = "/test";
        for (Integer i=0; i<=149; i++){
            str += "\n/next";
        }
        //str += "/stop";
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        ConsoleBot bot = new ConsoleBot(new Scanner(in), new PrintStream(outContent));
        bot.run();
        String[] result = outContent.toString().split("\r\n");
        assertEquals("Вопросов нет.", result[result.length-1]);
        System.setOut(null);
    }

    /**
     * Проверка на то, что правильный ответ будет принят с любым регистром написания.
     *
     * @result Корректная оценка ботом ответа пользователя.
     */
    @Test
    public void checkRegister()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("/test\nПрОсТоЙ".getBytes());
        ConsoleBot bot = new ConsoleBot(new Scanner(in), new PrintStream(outContent));
        bot.run();
        assertEquals("Правильный ответ!", outContent.toString().split("\r\n")[1]);
    }
}
