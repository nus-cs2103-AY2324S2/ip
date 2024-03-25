package bot;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class is responsible for reading and writing tasks to a file.
 */
public class Storage {
    private String filePath;
    private Ui ui = new Ui();

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The path of the file to read from and write to.
     * @throws DukeException If an error occurs while creating the file.
     */
    public Storage(String filePath) throws DukeException {
        assert filePath != null : "File path is null";
        this.filePath = filePath;
        try {
            ui.printMessage("Reading file from " + filePath + "...\n");
            createFolder();
            createFile();
        } catch (IOException e) {
            throw new DukeException("Error creating file");
        }
    }

    private void createFolder() throws IOException {
        Path path = Paths.get(filePath).getParent();
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        assert Files.exists(path) : "Folder was not created successfully";
    }

    private void createFile() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        assert Files.exists(path) : "File was not created successfully";
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = Task.fromString(line);
                assert task != null : "Task was not loaded successfully";
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Tasks are null";
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString());
        }
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
        }
        Files.write(path, lines);
        assert Files.readAllLines(path).equals(lines) : "Tasks were not saved successfully";
    }
}