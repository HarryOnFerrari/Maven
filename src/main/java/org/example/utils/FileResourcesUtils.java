package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Чтение файла из файла с расширением .txt
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class FileResourcesUtils {
    /**
     * Конструктор, считывающий содержание файла Tests.txt
     */
    public final InputStream INPUTSTREAM = this.getClass()
            .getClassLoader().getResourceAsStream("Tests.txt");
    
    /**
     * Чтение файла и добавление полученных строк в лист
     *
     * @exception IOException
     * @return лист чередующихся вопросов и ответов на них, нулевой элемент - вопрос
     */
    public LinkedList<String> makeListQuestions() {
        BufferedReader br = null;
        LinkedList<String> list = new LinkedList<>();
        try {
            br = new BufferedReader(new InputStreamReader(INPUTSTREAM));
            String str = null;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}