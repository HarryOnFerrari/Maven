package org.example.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Чтение из ресурсов файла с расширением .html
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class FileHTMLUtils {
    /**
     * Поле с содержаним файла *.html
     */
    private InputStream inputStream;
    /** Текстовая константа начала вопроса или ответа */
    private static final String FILE_START = "\"TermText notranslate lang-ru\">";
    /** Текстовая константа конца вопроса или ответа */
    private static final String FILE_END = "</span>";
    /**
     * Добавление полученных строк в лист с обработкой от лишних символов
     *
     * @return лист чередующихся вопросов и ответов на них, нулевой элемент - вопрос
     */
    public List<String> makeListQuestions() {
        String content = readFiles();
        List<String> list = new LinkedList<>();

        long endOfTheEnd = content.indexOf("Модули из той же папки",0);
        int indexStart = content.indexOf(FILE_START, 0);
        int indexEnd = content.indexOf(FILE_END, indexStart);
        while (indexStart != -1) {
            indexStart = content.indexOf(FILE_START, indexStart);
            if (indexEnd > endOfTheEnd) {
                break;
            }
            if (indexStart != -1) {
                indexEnd = content.indexOf(FILE_END, indexStart);
                list.add(content.substring(indexStart + FILE_START.length(), indexEnd).replace("<br>", "\n"));
                indexStart++;
            }
        }
        return list;
    }

    /**
     * Чтение файла в формат String
     */
    private String readFiles(){
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * Процедура определения файла для чтения
     * @param link - ключевое слово, определяющее через enum ссылку из ресурсов
     */
    public void setInputStream(String link) {
        inputStream = this.getClass().getClassLoader().getResourceAsStream(link);
    }
}
