package lesson3.history;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java Core. Professional level. Lesson 3
 *
 * @author Zurbaevi Nika
 */
public class HistoryManager {
    public static void saveHistory(String name, StringBuilder chatLog) {
        try {
            File history = new File("history_" + name + ".txt");
            if (!history.exists()) {
                history.createNewFile();
            }
            PrintWriter fileWriter = new PrintWriter(new FileWriter(history, true));
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(chatLog));
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadHistory(String name) {
        File history = new File("history_" + name + ".txt");
        try (Stream<String> stream = Files.lines(history.toPath())) {
            return stream.limit(100).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
