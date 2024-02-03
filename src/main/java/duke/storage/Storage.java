package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import duke.tasks.Task;

/**
 * Manages storage operations for Duke application tasks.
 * <p>
 * This class handles the loading, adding, and saving of task objects to a file.
 * It maintains an in-memory list of tasks for quick access and manipulation.
 * </p>
 */
public class Storage {
    private static final String FILE_NAME = "rah.txt";
    private static final Path PROJECT_DIR = Paths.get(System.getProperty("user.dir"));
    private static final Path DATA_DIR = Paths.get(PROJECT_DIR.toString(), "data");
    private static final Path FILE_PATH = Paths.get(DATA_DIR.toString(), FILE_NAME);
    private static ArrayList<Task> tasks = new ArrayList<>();

    public void writeToFile(ArrayList<Task> inventory) throws IOException {
        File file = FILE_PATH.toFile();
        // Ensure the directory exists
        file.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(file, false)) { // false to overwrite
            for (Task task : inventory) {
                fw.write(task.toString() + System.lineSeparator());
            }
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public ArrayList<Task> load() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (Task s : tasks) {
            result.append(count).append(". ").append(s.toString()).append("\n");
            count++;
        }
        return result.toString();
    }
}