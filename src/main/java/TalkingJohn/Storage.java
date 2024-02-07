package TalkingJohn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles the saving and loading of tasks to and from a file.
 */
public class Storage {
    private final String filePath;
    private final List<Task> taskArr;

    /**
     * Constructs a Storage object with the specified file path and task list.
     *
     * @param filePath The file path where the tasks are stored.
     * @param taskArr  The list containing the tasks.
     */
    public Storage(String filePath, List<Task> taskArr) {
        this.filePath = filePath;
        this.taskArr = taskArr;
    }

    /**
     * Saves the tasks to the file.
     */
    public void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskArr) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list containing the loaded tasks.
     */
    public List<Task> loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse each line and create corresponding Task object
                // Add the Task object to the taskArr list
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskArr;
    }
}