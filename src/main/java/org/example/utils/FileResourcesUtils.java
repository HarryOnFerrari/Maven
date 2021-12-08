package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Чтение файла из файла с расширением .txt
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class FileResourcesUtils {
    /** Имя читаемого файла */
    private static final String filename = "Subjects.txt";
    /**
     * Чтение файла и добавление полученных строк в лист
     *
     * @return лист доступных учебных предметов
     */
    public List<String> makeListWords() {
        List<String> list = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass()
                .getClassLoader().getResourceAsStream(filename))))){
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
