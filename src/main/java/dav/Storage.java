package dav;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks from/to a file.
 */
class Storage {

    private static final String FILE_PATH = "./data/dav.txt";

    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * @param filePath The file path for loading and saving tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor.
     * @return List of tasks loaded from the file.
     * @throws DavException If there is an error loading tasks from the file.
     */
    public List<Task> load() throws DavException {
        try {
            List<Task> tasks = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                Task task = Task.parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            return tasks;
        } catch (IOException e) {
            throw new DavException("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the file specified in the constructor.
     * @param tasks List of tasks to be saved to the file.
     * @throws DavException If there is an error saving tasks to the file.
     */
    public void save(List<Task> tasks) throws DavException {
        try {
            Path filePath = Paths.get(FILE_PATH);
            StringBuilder data = new StringBuilder();

            for (Task task : tasks) {
                data.append(task.toDataString()).append("\n");
            }

            Files.write(filePath, data.toString().getBytes());
        } catch (IOException e) {
            throw new DavException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
