package Shirmin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles storage operations for Shirmin application tasks.
 * <p>
 * Responsible for saving tasks to a file and reading tasks from a file.
 * It interacts with the Ui component for error reporting and the Parser component for parsing tasks.
 */
public class Storage {
    final Parser parser;

    /**
     * Initializes a new Storage object.
     *
     * @param parser The Parser component used for parsing tasks.
     */
    public Storage (Parser parser) {
        this.parser = parser;
    }

    /**
     * Saves a single Task object to the storage file.
     * <p>
     * The task is appended to the end of the file. If an IOException occurs, an error message is displayed.
     *
     * @param task The Task object to be saved.
     */
    public void saveTaskToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter("./data/Shirmin.txt", true);

            String taskLine = parser.formatTaskForFile(task);


            fileWriter.write(taskLine + "\n");
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Saves a list of Task objects to the storage file.
     * <p>
     * This method overwrites the existing file with the entire list of tasks.
     * If an IOException occurs, an error message is displayed.
     *
     * @param taskList The list of Task objects to be saved.
     */
    public void saveAllTasksToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter("./data/Shirmin.txt", false);
            for (Task task : taskList) {
                String taskLine = parser.formatTaskForFile(task);
                fileWriter.write(taskLine + "\n");
            }
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }
}
