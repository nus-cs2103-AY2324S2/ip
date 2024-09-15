package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.task.Task;

/**
 * Represents the storage for the tasklist.
 */
public class Storage {
    private static final Path path = Paths.get("data", "duke.txt");
    /**
     * Loads the data from the file.
     * If the file does not exist, a new file will be created.
     *
     * @return The data from the file.
     * @throws IOException If an error occurs while reading from the file.
     */
    public static List<String> loadData() throws IOException {
        Path dirPath = path.getParent();
        assert dirPath != null;
        Files.createDirectories(dirPath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        return Files.readAllLines(path);
    }

    /**
     * Saves the data to the file.
     *
     * @param todolist The data to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveData(List<Task> todolist) throws IOException {
        Path dirPath = java.nio.file.Paths.get("data");
        Path fullPath = dirPath.resolve("duke.txt");
        Files.createDirectories(dirPath);

        try (FileWriter fw = new FileWriter(fullPath.toString(), false)) {
            String entry;
            for (Task t : todolist) {
                entry = t.getDataString() + "\n";
                fw.write(entry);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            throw e;
        }
    }
}
