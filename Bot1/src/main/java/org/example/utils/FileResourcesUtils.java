package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class FileResourcesUtils {
    public final InputStream INPUTSTREAM = this.getClass()
            .getClassLoader().getResourceAsStream("Tests.txt");

    public Queue read_files() throws IOException {
        BufferedReader br = null;
        Queue queue = new LinkedList();
        try {
            br = new BufferedReader(new InputStreamReader(INPUTSTREAM));
            String str = null;
            while ((str = br.readLine()) != null) {
                queue.add(str);
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
        return queue;
    }
}