package utils;

import java.io.*;
import java.util.LinkedList;

/**
 * Чтение файла из файла с расширением .html
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class FileHTMLUtils {
    /**
     * Добавление полученных строк в лист с обработкой от лишних символов
     *
     * @return лист чередующихся вопросов и ответов на них, нулевой элемент - вопрос
     */
    public LinkedList<String> makeList() {
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
                //list.add("\n"); //из-за этой строки кажется, что каждый вывод обрамлён запятыми,
                                // но это не проблема, это чисто для самопроверки вывода, можно удалить
                indexStart++;
            }
        }
        return list;
    }

    /**
     * Конструктор, считывающий содержание файла Карточки БЖД 1 _ Quizlet.html
     */
    public final InputStream INPUTSTREAM = this.getClass()
            .getClassLoader().getResourceAsStream("Карточки английский _ Quizlet.html");

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
