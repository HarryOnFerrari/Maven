package org.example;
import Console.Testing;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * junit тестирование класса Testing
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class TestingTest {
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
        Testing test = new Testing();
        String str = "";
        for (Integer i=0; i<10; i++){
            if (i%2 == 1) str += " /next";
            else str += "answer";
        }
        str += "/stop";
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        test.makeTest(new Scanner(in), new PrintStream(outContent));
        String[] result = outContent.toString().split("\n");
        assertEquals("Вопросов больше нет\r", result[result.length-1]);
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
        Testing test = new Testing();
        ByteArrayInputStream in = new ByteArrayInputStream("Да /stop".getBytes());
        test.makeTest(new Scanner(in), new PrintStream(outContent));
        assertEquals("Верно!!!\r", outContent.toString().split("\n")[1]);
    }
}

