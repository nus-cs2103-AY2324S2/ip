package bentley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class responsible for loading tasks from and writing tasks to a file.
 */
public class Storage {

    private String filePath;
    private boolean fileIsOpen;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are loaded from and written to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileIsOpen = true;
    }

    /**
     * Loads tasks from the file specified in the constructor.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */

    /**
     * Checks if the file is open.
     *
     * @return true if the file is open, false otherwise.
     */
    public boolean checkFileIsOpen() {
        return fileIsOpen;
    }
    public ArrayList<TaskList.Task> loadTasks() {
        ArrayList<TaskList.Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();

                // Assuming TaskList.Task has a constructor
                TaskList.Task task = new TaskList.Task(taskData);
                tasks.add(task);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            fileIsOpen = false;
        }
        return tasks;
    }


    /**
     * Writes tasks from the provided ArrayList to the file.
     *
     * @param tasks The ArrayList of tasks to be written to the file.
     */
    public void writeTasks(ArrayList<TaskList.Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (TaskList.Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
            System.out.println("Tasks written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing tasks to file: " + e.getMessage());
        }
    }
}
