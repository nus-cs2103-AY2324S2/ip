package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code Storage} class is used to read and write data to the file.
 */
public class Storage {
    private static final Path PATH_TO_TASKS_FILE = Paths.get("echo_bot_tasks.txt");
    private static final Path PATH_TO_TMP_FILE = Paths.get(PATH_TO_TASKS_FILE.toString() + ".tmp");

    /**
     * Deletes the file if it exists.
     */
    public void deleteFile() {
        try {
            Files.delete(PATH_TO_TASKS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a list of strings, representing the tasks, from the file.
     *
     * @return the list of strings
     */
    public List<String> loadFromFile() {
        try {
            return Files.readAllLines(PATH_TO_TASKS_FILE);
        } catch (FileNotFoundException e) {
            // ignore
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param s the list of tasks in string format, after being serialized by
     *          {@link Task#serializeToCommand}
     */
    public void saveToFile(List<String> s) {
        // We write to a temporary file first, then rename it to the actual file.
        // This is to prevent the file from being corrupted if the program crashes.
        try {
            try (PrintWriter out = new PrintWriter(PATH_TO_TMP_FILE.toFile())) {
                for (String line : s) {
                    out.print(line);
                }
            }
            Files.move(PATH_TO_TMP_FILE, PATH_TO_TASKS_FILE, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
};
