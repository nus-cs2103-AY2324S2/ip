package BotChat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of task data from/to the hard disk in the botChat application.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where the task data is stored. Must not be null or empty.
     */
    public Storage(String filePath) {
        // Assert that filePath is not null or empty
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads task data from the hard disk when the botChat application starts up.
     *
     * @return A TaskList containing the tasks loaded from the hard disk.
     * @throws RuntimeException If an error occurs during the loading process.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            // Assert that file exists
            assert file.exists() : "File does not exist";

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.convertTask(line);
                tasks.addTask(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (BotChatException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    /**
     * Saves the changes to the hard disk by overwriting the existing task data.
     *
     * @param tasks The list of tasks to be saved to the hard disk. Must not be null.
     */
    public static void saveTaskToHardDisk(ArrayList<Task> tasks) {
        // Assert that tasks is not null
        assert tasks != null : "Task list should not be null";

        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
