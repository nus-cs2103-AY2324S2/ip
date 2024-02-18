package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the reading and writing of tasks to a file.
 */
public class Storage {
    /**
     * The default file path for storing tasks.
     */
    private String FILE_NAME = "src/main/java/data/duke.txt";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private String FILE_PATH = System.getProperty("user.dir") + File.separator + FILE_NAME;

    /**
     * The actual file path to be used for reading and writing tasks.
     */
    private String filepath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path to be used for reading and writing tasks.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    /**
     * Saves the tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveToFile(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Could not save tasks to file " + e.getMessage());
        }
    }
    /**
     * Loads tasks from the specified file.
     *
     * @return The list of tasks loaded from the file.
     */

    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //adding the task from the file to be read
                tasks.add(Task.fromFileString(line));
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Creating new Data File...");
            //saveToFile(0, tasks);
        } catch (IOException e) {
            System.out.println("Error loading file" + e.getMessage());
        }
        return tasks;

    }

}
