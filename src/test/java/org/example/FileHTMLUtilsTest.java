package org.example;

import org.example.utils.FileHTMLUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Тесты на класс {@link FileHTMLUtils}
 *
 * @author Бабакова Анастасия, Пономарева Дарья
 */
public class FileHTMLUtilsTest {
    /** Поле с парами "ссылка - список вопросов к повторению" для всех предметов */
    private Map<String, Map.Entry<String, Map<String, String>>> subjects;
    /** Поле очередь вопросов и верных ответов */
    private List<String> listQuestions;

    /**
     * Тест на корректное чтение распарсенных вопросов/ответов с сайтов
     */
    @Test
    public void testRightRead()
    {
        FileHTMLUtils fileHTMLUtils = new FileHTMLUtils();
        subjects = new HashMap<>();
        for (Subjects sub: Subjects.values()) {
            subjects.put(sub.toString(),  Map.entry(
                    sub.value(),
                    new HashMap<>()
            ));
        }
        fileHTMLUtils.setInputStream(subjects.get("MATHS").getKey());
        listQuestions = fileHTMLUtils.makeListQuestions();
        Assert.assertEquals("10^2", listQuestions.get(0));
        Assert.assertEquals("100", listQuestions.get(1));
        Assert.assertEquals("16^2", listQuestions.get(12));
        Assert.assertEquals("256", listQuestions.get(13));

        fileHTMLUtils.setInputStream(subjects.get("ENGLISH").getKey());
        listQuestions = fileHTMLUtils.makeListQuestions();
        Assert.assertEquals("simple", listQuestions.get(0));
        Assert.assertEquals("простой",  listQuestions.get(1));
        Assert.assertEquals("polite", listQuestions.get(12));
        Assert.assertEquals("вежливый", listQuestions.get(13));

        fileHTMLUtils.setInputStream(subjects.get("RUSSIAN").getKey());
        listQuestions = fileHTMLUtils.makeListQuestions();
        Assert.assertEquals("аб_жур", listQuestions.get(0));
        Assert.assertEquals("а", listQuestions.get(1));
        Assert.assertEquals("пласт_лина", listQuestions.get(12));
        Assert.assertEquals("и", listQuestions.get(13));
    }
}
