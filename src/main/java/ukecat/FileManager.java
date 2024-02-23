package ukecat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages the loading and updating of tasks from/to a data file.
 * Handles interactions between the application's Storage, Parser, and tasks file.
 */
public class FileManager {
    /**
     * Loads tasks from the data file during application startup.
     * If the data file is not found, it creates a new file.
     * Reads data line by line, and for each line, asks Storage to load the task.
     * Storage, in turn, asks Parser to parse the line and creates a Task to be added.
     */
    public static void loadTasks() {
        File file = new File("data/taskData.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                Storage.loadTask(scanner.nextLine());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                File parentDir = file.getParentFile();

                if (!parentDir.exists() && !parentDir.mkdirs()) {
                    System.out.println("Failed to create parent directories for the file.");
                }

                boolean isCreated = file.createNewFile();

                if (isCreated) {
                    System.out.println("File not found, I created one for you!");
                } else {
                    System.out.println("Failed to create the file.");
                }
            } catch (IOException ioException) {
                System.out.println("IO error occurred when creating the file: " + ioException.getMessage());
            }
        }
    }

    /**
     * Updates the tasks file by rewriting it with data from Storage.
     * Each task is converted to CSV format using Parser's parseTaskToCsv method.
     */
    public static void updateTasks() {
        try (FileWriter fileWriter = new FileWriter("data/taskData.txt")) {
            for (Task task : Storage.getTasks()) {
                fileWriter.write(Parser.parseTaskToCsv(task) + "\n");
            }
        } catch (IOException ioException) {
            System.out.println("IO error occurred while updating.");
        }
    }
}
