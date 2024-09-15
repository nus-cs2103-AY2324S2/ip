package rochin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * The Storage class handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from the specified file.
     *
     * @return A list of strings representing the loaded tasks.
     * @throws RochinException If an error occurs while loading tasks from the file.
     */
    public List<Task> load() throws RochinException {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                //file.createNewFile();
                Files.createDirectories(Paths.get(this.filePath).getParent());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.createTaskFromFileString(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new RochinException("Error loading tasks from file. ");
        }
        return tasks;
    }

    /**
     * Save tasks to the specified file.
     *
     * @param lines The list of strings representing tasks to be saved.
     * @throws RochinException If an error occurs while saving tasks to the file.
     */
    public void save(List<String> lines) throws RochinException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            throw new RochinException("Error saving tasks to file.");
        }
    }
}
