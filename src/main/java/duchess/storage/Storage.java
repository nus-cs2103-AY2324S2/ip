package duchess.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duchess.DuchessException;
import duchess.TaskList;
import duchess.parser.Parser;
import duchess.task.Task;

/**
 * Storage class manages the loading and saving of tasks from/to a file.
 * It handles file I/O operations and utilizes a Parser to convert file data into Task objects.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * Initializes the file path and creates a Parser instance.
     *
     * @param filePath the file path to store tasks
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path is null or empty";
        this.filePath = filePath;
        createDirectoryIfNotExists(filePath);
    }

    /**
     * Helper method to create the directory if it doesn't exist.
     *
     * @param filePath the file path to check and create directory
     */
    private void createDirectoryIfNotExists(String filePath) {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs(); // Creates parent directories if they don't exist
        }
    }

    /**
     * Loads tasks from the file specified in the constructor.
     * Parses each line of the file into Task objects and returns them as a list.
     *
     * @return the list of tasks loaded from the file
     * @throws DuchessException if an error occurs during file reading or parsing
     */
    public ArrayList<Task> loadData() throws DuchessException {
        TaskList taskList = new TaskList();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = Parser.parseTaskFromFileString(line); // Parse task from file line
                if (task != null) {
                    taskList.getTasks().add(task); // Add task to the list
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskList.getTasks();
    }

    /**
     * Saves the given list of tasks to the file specified in the constructor.
     * Each task is written as a line in the file.
     *
     * @param taskList the list of tasks to be saved
     */
    public void saveData(TaskList taskList) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileString() + "\n"); // Write each task to file
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

}
