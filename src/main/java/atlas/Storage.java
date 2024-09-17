package atlas;

import atlas.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The Storage class handles loading from and saving to the task data file.
 */
public class Storage {
    private String filePath;

    private TaskList tasks;

    /**
     * Constructs a new Storage object.
     *
     * @param tasks    The TaskList to be loaded from or saved to the file.
     * @param filePath The file path of the data file.
     */
    public Storage(TaskList tasks, String filePath) {
        this.tasks = tasks;
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file into the TaskList.
     */
    public void load() {
        File file = new File(filePath);
        ensureFileExists();
        loadIntoFile(file);


    }

    private void loadIntoFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.parseLineToTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to load tasks: " + e.getMessage());
        }
    }

    /**
     * Saves tasks from the TaskList to the data file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     */
    public void save(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Ensures that the data file and its parent directories exist.
     * If they do not exist, it attempts to create them.
     */
    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File directory = file.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }
}
