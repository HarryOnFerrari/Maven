package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

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
     * @return лист доступных учебных предметов
     */
    public List<String> makeListWords() {
        BufferedReader bufferedReader;
        List<String> list = new LinkedList<>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(INPUTSTREAM_SUBJECT));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
