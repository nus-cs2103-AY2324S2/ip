package chipchat.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import chipchat.action.Action;
import chipchat.parser.Parser;
import chipchat.task.Task;
import chipchat.task.TaskList;

/**
 * Represents the storage for Chipchat, used to read/write task data to storage files.
 */
public class Storage {
    private static final String STORAGE_PATH = "/src/main/data/storage.txt";
    private final Path filePath;

    /**
     * Initializes the storage if no file path is given.
     * The following file path will be used:
     * path to top-level working directory + "/src/main/data/storage.txt"
     */
    public Storage() {
        this.filePath = createFilePath(System.getProperty("user.dir"), STORAGE_PATH);
    }

    /**
     * Initializes the storage using the given file path
     *
     * @param filePath file path of where the storage file will be created and stored at.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    private static Path createFilePath(String pathToProjectRoot, String filePathFromProjectRoot) {
        String absPath = pathToProjectRoot + filePathFromProjectRoot;
        String[] paths = absPath.split("/");
        return Paths.get(paths[0], Arrays.copyOfRange(paths, 1, paths.length));
    }

    /**
     * Saves task data from a given task list to the pre-specified file path.
     *
     * @param tasks task list to retrieve parsable data string of tasks from
     */
    public void save(TaskList tasks) {
        try {
            if (Files.notExists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException x) {
            System.err.format("File existence check, IOException: %s\n", x);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write(tasks.dataString());
        } catch (IOException x) {
            System.err.format("File write, IOException: %s\n", x);
        }
    }

    /**
     * Loads task data from storage file at pre-specified file path.
     *
     * @return an array list of tasks that has been initialized with loaded data
     */
    public ArrayList<Action> load() {
        if (Files.notExists(this.filePath)) {
            return new ArrayList<>();
        }

        try (BufferedReader reader = Files.newBufferedReader(this.filePath, StandardCharsets.UTF_8)) {
            ArrayList<Action> actions = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                Action addTask = Parser.parseLoadedTask(line);
                actions.add(addTask);
            }
            return actions;
        } catch (IOException x) {
            System.err.format("IOException: %s\n", x);
            return null;
        }
    }
}
