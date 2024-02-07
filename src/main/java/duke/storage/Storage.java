package duke.storage;

import duke.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor.
     *
     * @return A list of tasks loaded from the file.
     * @throws DukeException If an error occurs while loading tasks from the file.
     */
    public List<Task> load() throws DukeException {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                Task task = null;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2], parts[1].equals("1"));
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3], parts[1].equals("1"));
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4], parts[1].equals("1"));
                        break;
                }
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Saves the list of tasks to the file specified in the constructor.
     *
     * @param tasks The list of tasks to be saved.
     * @throws DukeException If an error occurs while saving tasks to the file.
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            File directory = new File(filePath).getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
