package org.example;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestingTest {

    @Test
    public void repeatingTests()
    {
        Testing test = new Testing();
        String str = "";
        for (Integer i=0; i<10; i++){
            if (i%2 == 1) str += " /next";
            else str += "answer";
        }
        str += "/stop";
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        System.setIn(in);
        test.makeTest();
    }

    @Test
    public void checkRegister()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Testing test = new Testing();
        ByteArrayInputStream in = new ByteArrayInputStream("Да /stop".getBytes());
        System.setIn(in);
        test.makeTest();
        assertEquals("Верно!!!\r", outContent.toString().split("\n")[1]);
        System.setOut(null);
    }
}

