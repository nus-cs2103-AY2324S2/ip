package tony;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {

    private String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be loaded from and saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns them as a TodoList.
     *
     * @return A TodoList containing the loaded tasks.
     */
    public TodoList load() {
        TodoList list = new TodoList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.loadTask(line);
            }
            System.out.println("Tasks have been loaded from " + filePath);
        } catch (Exception e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return list;
    }

    /**
     * Saves tasks represented as text to the specified file.
     *
     * @param tasksText The text representation of tasks to be saved.
     */
    public void saveToFile(String tasksText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(tasksText);
            System.out.println("Tasks have been saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving tony.tasks to file: " + e.getMessage());
        }
    }
}
