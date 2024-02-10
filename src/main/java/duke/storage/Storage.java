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
 * <p>
 * The storage is implemented using a file-based approach, where tasks are written
 * to and read from a specified file. The file is stored in a directory named "data"
 * within the project's root directory.
 * </p>
 */
public class Storage {
    // Constants for file and directory names
    private static final String FILE_NAME = "rah.txt";
    private static final Path PROJECT_DIR = Paths.get(System.getProperty("user.dir"));
    private static final Path DATA_DIR = Paths.get(PROJECT_DIR.toString(), "data");
    private static final Path FILE_PATH = Paths.get(DATA_DIR.toString(), FILE_NAME);

    // In-memory list of tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Writes the given list of tasks to the file specified in {@code FILE_PATH}.
     * Overwrites the existing content of the file.
     *
     * @param inventory The list of tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
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

    /**
     * Adds a task to the in-memory list of tasks.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Retrieves the in-memory list of tasks.
     *
     * @return An ArrayList containing the tasks stored in memory.
     */
    public ArrayList<Task> load() {
        return tasks;
    }

    /**
     * Returns a string representation of the tasks stored in the in-memory list.
     *
     * @return A formatted string listing the tasks.
     */
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

