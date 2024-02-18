package duke;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;

public class Storage {
    private static Path PATH_TO_TASKS_FILE = Paths.get("echo_bot_tasks.txt");

    public void deleteFile() {
        try {
            Files.delete(PATH_TO_TASKS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadFromFile() throws DukeException {
        try {
            return Files.readAllLines(PATH_TO_TASKS_FILE);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Saves the current list of tasks to the file.
     */
    public void saveToFile(List<String> s) {
        // We write to a temporary file first, then rename it to the actual file.
        // This is to prevent the file from being corrupted if the program crashes.
        final Path PATH_TO_TMP_FILE = Paths.get(PATH_TO_TASKS_FILE.toString() + ".tmp");
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
