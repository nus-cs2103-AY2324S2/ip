package duke;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
/**
 * Manages the loading and saving of tasks from/to a file.
 */
public class Storage {
    private String filePath;
    private static final String FILE_PATH = ".data/text/duke.txt";

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from the specified file path.
     *
     * @return A list of tasks loaded from the file.
     * @throws DukeException If an error occurs while loading tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.createTaskFromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }
    /**
     * Saves a list of tasks to the specified file path.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
