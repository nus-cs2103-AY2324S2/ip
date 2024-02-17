package duke.io;
import duke.task.Task;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage - Handles saving and loading tasks to and from a file.
 */
public class Storage {
    private static final String FILEPATH = "./data/duke.txt";

    /**
     * Creates the data folder if it does not exist.
     */
    public static void createFolder() {
        Path folder = Paths.get("./data/");
        assert Files.notExists(folder) : "Data folder already exists";

        if (Files.notExists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                e.printStackTrace();
                assert false : "Failed to create data folder";
            }
        }
    }

    /**
     * Saves tasks to a file.
     *
     * @param tasks ArrayList of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks should not be null";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH, true))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "Failed to save tasks to file";
        }
    }

    /**
     * Loads tasks from a file.
     *
     * @param tasks ArrayList to store the loaded tasks.
     */
    public static void loadFile(ArrayList<Task> tasks) {
        File file = new File(FILEPATH);
        if (!file.exists()) {
            System.out.println("File does not exist: " + FILEPATH);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Loaded line: " + line);

                tasks.add(new Task(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
