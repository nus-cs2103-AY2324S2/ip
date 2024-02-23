package alfred.util;

import alfred.task.Task;
import alfred.task.TaskConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the loading and saving of tasks to a file.
 * This class encapsulates file operations, allowing tasks to be persisted to disk and loaded back into the application.
 */
public class FileManager {
    private String path;

    /**
     * Constructs a FileManager with a specified file path for saving and loading tasks.
     *
     * @param path The file path where tasks are saved and loaded from.
     */
    public FileManager(String path) {
        this.path = path;
    }

    /**
     * Loads tasks from the file specified by the path.
     * If the file does not exist, it creates a new file. Each line in the file is expected to represent a single task.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadFile() {
        List<String> tasks = new ArrayList<>();
        File file = new File(this.path);

        if (!file.exists()){
            try {
                file.getParentFile().mkdirs();
                boolean exists = file.createNewFile();
                assert exists : "File created successfully";
                System.out.println("New task file created at: " + this.path);
            } catch (IOException e) {
                System.out.println("Failed to create save file");
            }
        } else {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String currLine = scanner.nextLine();
                    tasks.add(currLine);
                }
                scanner.close();

            } catch (IOException e) {
                System.out.println("Error loading save file");
            }
        }

        return TaskConverter.loadConvert(tasks);
    }

    /**
     * Saves the current list of tasks to the file specified by the path.
     * Each task is converted to a string representation and written as a new line in the file.
     *
     * @param taskList The ArrayList of Task objects to be saved.
     */
    public void saveFile(ArrayList<Task> taskList) {
        List<String> tasks = TaskConverter.saveConvert(taskList);
        try {
            File file = new File(this.path);
            FileWriter writer = new FileWriter(file);
            for (String task : tasks) {
                writer.write(task + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
    }

}
