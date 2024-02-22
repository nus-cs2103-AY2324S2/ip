package james.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import james.exception.DukeException;
import james.parser.Parser;
import james.tasks.Task;

/**
 * Represents the storage of tasks in the hard disk.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage with the given file path.
     *
     * @param filePath File path to store the tasks in.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file path.
     *
     * @return List of tasks loaded from the file path.
     * @throws IOException If an error occurs while loading the tasks.
     */
    public ArrayList<Task> load() throws IOException {
        Path path = Paths.get(this.filePath);
        assert Files.exists(path) || Files.notExists(path.getParent())
                : "Task file or parent directory state is inconsistent";
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(path);
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            try {
                Task task = Parser.parseLineToTask(line);
                tasks.add(task);
            } catch (DukeException e) {
                System.out.println("An error occurred while loading tasks: " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file path.
     *
     * @param tasks List of tasks to save to the file path.
     * @throws IOException If an error occurs while saving the tasks.
     */
    public void save(List<Task> tasks) throws IOException {
        List<String> lines = tasks.stream()
                                  .map(Task::toFileFormat)
                                  .collect(Collectors.toList());
        Path path = Paths.get(this.filePath);
        Files.write(path, lines);
    }
}
