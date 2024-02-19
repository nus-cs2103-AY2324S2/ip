package baron.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * In charge of managing simple file IO such as reading and writing.
 */
public class FileUtils {

    /**
     * Returns a list of strings from a given file path.
     *
     * @param filePath path of file to read
     * @return A list of strings if the file exists, else, it's empty
     */
    public static List<String> read(Path filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    /**
     * Wrties the given list of lines into a file. This is an overwrite operation, not append.
     *
     * @param filePath path of file to write
     * @param lines    list of strings to write to file.
     */
    public static void write(Path filePath, List<String> lines) {
        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
