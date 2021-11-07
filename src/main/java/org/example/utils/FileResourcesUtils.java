package org.example.utils;

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
     * Конструктор, считывающий содержание файла Subjects.txt
     */
    public final InputStream INPUTSTREAM_SUBJECT = this.getClass()
            .getClassLoader().getResourceAsStream("Subjects.txt");
    
    /**
     * Чтение файла и добавление полученных строк в лист
     *
     * @exception IOException
     * @return лист доступных учебных предметов
     */
    public LinkedList<String> makeListWords() {
        BufferedReader br = null;
        LinkedList<String> list = new LinkedList<>();
        try {
            br = new BufferedReader(new InputStreamReader(INPUTSTREAM_SUBJECT));
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
