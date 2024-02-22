package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to and from a file.
 * This class encapsulates all file operations, providing methods to load tasks from a file into the application
 * and save the current state of tasks back to the file.
 */
public class Storage {
    /**
     * The file path where tasks are stored.
     */
    private String filePath;

    /**
     * Constructs a new Storage instance.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * This method reads the file line by line, decoding each line into a Task object which is then added
     * to a list of tasks that is returned.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws FileNotFoundException If the specified file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = Parser.decodeTask(line);
            if (task != null) {
                loadedTasks.add(task);
            }
        }
        scanner.close();

        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     * Each task is converted to a string format suitable for file storage and then written to the file.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        new File("." + File.separator + "data").mkdirs();
        PrintWriter writer = new PrintWriter(new File(filePath));

        for (Task task : tasks) {
            writer.println(task.toFileFormat());
        }

        writer.close();
    }
}

