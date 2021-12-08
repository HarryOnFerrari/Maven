package org.example;
import org.example.platforms.ConsoleBot;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
     * @exception IOException
     * @result По завершению списка вопросов пользователь получает сообщение,
     *         ошибок не возникает.
     */
    @Test
    public void repeatingTests() throws IOException {
        String str = "ENGLISH\n/test";
        for (int i = 0; i<=149; i++){
            str += "\n/next";
        }

        try (ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes())) {
            try (ByteArrayOutputStream outContent = new ByteArrayOutputStream()) {
                ConsoleBot bot = new ConsoleBot(new Scanner(in), new PrintStream(outContent));
                bot.run();
                String[] result = outContent.toString().split("\r\n");
                assertEquals("Вопросов нет. \nДля продолжения отправьте /start", result[result.length-1]);
            }
        }
    }

    /**
     * Проверка на то, что правильный ответ будет принят с любым регистром написания.
     *
     * @exception IOException
     * @result Корректная оценка ботом ответа пользователя.
     */
    @Test
    public void checkRegister() throws IOException {
        try (ByteArrayInputStream in = new ByteArrayInputStream("ENGLISH\n/test\nПрОсТоЙ".getBytes())) {
           try (ByteArrayOutputStream outContent = new ByteArrayOutputStream()) {
               ConsoleBot bot = new ConsoleBot(new Scanner(in), new PrintStream(outContent));
               bot.run();
               String[] result = outContent.toString().split("\r\n");
               assertEquals("Правильный ответ!", result[result.length-3]);
           }
        }
    }
}
