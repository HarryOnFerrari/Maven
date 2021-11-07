package org.example.utils;

import java.io.*;
import java.util.LinkedList;

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
    public LinkedList<String> makeListQuestions() {
        String content = "";
        try {
            content = readFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<String> list = new LinkedList<>();
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

    public void setINPUTSTREAM(String link) {
        this.INPUTSTREAM = this.getClass()
                .getClassLoader().getResourceAsStream(link);
    }

    /**
     * Конструктор, считывающий содержание файла Карточки английский _ Quizlet.html
     */
    private InputStream INPUTSTREAM; // = this.getClass()
          //  .getClassLoader().getResourceAsStream("Карточки английский _ Quizlet.html");

    /**
     * Чтение файла в формат String
     *
     * @exception IOException
     */
    public String readFiles() throws IOException {
        BufferedReader br = null;
        String result ="";
        try {
            br = new BufferedReader(new InputStreamReader(INPUTSTREAM));
            String str = null;
            while ((str = br.readLine()) != null) {
                result += str;
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
        return result;
    }
}
