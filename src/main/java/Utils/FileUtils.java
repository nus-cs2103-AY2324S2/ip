package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {
    public static String read(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line;
            do {
                line = reader.readLine();
                sb.append(line);
            } while (line != null);
            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return "";
        }
    }

    /**
     * This function simply helps with inserting a line at a given line number. It uses a string builder
     * to read in all pre-existing lines of the original file, and then at the specified line number, inserts
     * the new line, and then overwrites the file
     * @param filePath the path of the file to insert the line into
     * @param newLine the line to insert
     */
    public static void insert(Path filePath, String newLine) {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            lines.add(newLine);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The same as insert, just that we replace lines instead of inserting at specified index now
     * @param filePath
     * @param index this is 1-indexed, in accordance to how the application indexes
     * @param newLine
     */
    public static void update(Path filePath, int index, String newLine) {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            lines.set(index-1, newLine);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
