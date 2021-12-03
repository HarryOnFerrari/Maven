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
     * Добавление полученных строк в лист с обработкой от лишних символов
     *
     * @return лист чередующихся вопросов и ответов на них, нулевой элемент - вопрос
     */
    public List<String> makeListQuestions() {
        String content = readFiles();
        List<String> list = new LinkedList<>();
        String start = "\"TermText notranslate lang-ru\">";
        String end = "</span>";
        long endOfTheEnd = content.indexOf("Модули из той же папки",0);
        int indexStart = content.indexOf(start, 0);
        int indexEnd = content.indexOf(end, indexStart);
        while (indexStart != -1){
            indexStart = content.indexOf(start, indexStart);
            if (indexEnd > endOfTheEnd){
                break;
            }
            if (indexStart != -1) {
                indexEnd = content.indexOf(end, indexStart);
                list.add(content.substring(indexStart + start.length(), indexEnd).replace("<br>", "\n"));
                indexStart++;
            }
        }
        return list;
    }

    /**
     * Процедура определения файла для чтения
     * @param link - ключевое слово, определяющее через enum ссылку из ресурсов
     */
    public void setINPUTSTREAM(String link) {
        this.INPUTSTREAM = this.getClass()
                .getClassLoader().getResourceAsStream(link);
    }

    /**
     * Конструктор, считывающий содержание файла *.html
     */
    private InputStream INPUTSTREAM;

    /**
     * Чтение файла в формат String
     *
     * @exception IOException
     */
    public String readFiles(){
        //BufferedReader bufferedReader;
        String result ="";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(INPUTSTREAM))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
