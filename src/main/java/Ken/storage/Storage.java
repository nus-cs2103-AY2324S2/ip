package ken.storage;

import ken.task.Task;
import ken.exception.KenException;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class is responsible for saving and loading tasks to and from hard drive.
 *
 * The tasks are stored in a plain text file with one task per line.
 * The file is created in the "data" directory with the name "ken.txt".
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new Storage with the specified file path.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a list of tasks to the file specified in the constructor.
     *
     * @param tasks the list of tasks to be saved
     * @throws KenException if there is an error during file writing
     */
    public void saveTasks(List<Task> tasks) {
        try {
            File directory = new File("." + File.separator + "data");
            if (!directory.exists()) {
                directory.mkdirs();  // Create the directory if it doesn't exist
            }

            File file = new File(directory, "ken.txt");
            if (!file.exists()) {
                file.createNewFile();  // Create the file if it doesn't exist
            }

            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(task.toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file specified in the constructor.
     *
     * @return a list of tasks loaded from the file
     * @throws KenException if there is an error during file reading
     */
    public List<Task> loadTask() throws KenException {
        try {
            File directory = new File("." + File.separator + "data");
            if (!directory.exists()) {
                directory.mkdirs();  // Create the directory if it doesn't exist
                return new ArrayList<>();  // Return early, no need to load tasks from a non-existent file
            }

            File file = new File(directory, "ken.txt");
            if (!file.exists()) {
                return new ArrayList<>();  // Return early, no need to load tasks from a non-existent file
            }

            List<Task> tasks = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.parseFromFileString(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new KenException("Error loading tasks from file: " + e.getMessage());
        }
    }
}
