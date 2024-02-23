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

    /**
     * The name of the file used for storing tasks.
     */
    private String fileName;

    /**
     * Constructs a Storage object with the specified file name.
     *
     * @param fileName The name of the file used for storing tasks.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads tasks from the file and adds them to the provided ArrayList of tasks.
     *
     * @param tasks The ArrayList to which loaded tasks will be added.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskDescription = scanner.nextLine();
                taskList.add(new Task(taskDescription));
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Writes tasks from the provided ArrayList to the file.
     *
     * @param tasks The ArrayList of tasks to be written to the file.
     */
    public void writeTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
            System.out.println("Tasks written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
