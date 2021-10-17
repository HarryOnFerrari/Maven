package org.example;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.*;

public class Testing {
    //не знаю почему, но не робит
    public String makeTest(Update update) throws IOException{
        File file = new File("Tests.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String question = null;
        try {
            question = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.print(question);
        return question;
    }
}
