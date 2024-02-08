package storage;

import exceptions.DukeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents the interface for managing data transfer between the program and the local storage
 */
public class Storage {
    private static final String DEFAULT_PATH = "data.txt";
    private Path path;

    public Storage() {
        this.path = Paths.get(DEFAULT_PATH);
    }

    /**
     * Load task data from local storage
     * @return list of String representing the TaskList
     * @throws DukeException if IOException is thrown
     */
    public List<String> load() throws DukeException {
        try {
            if (!(Files.exists(path))) {
                Files.createFile(path);
            }
            List<String> fileOutput = Files.readAllLines(path);
            return fileOutput;

        } catch (IOException e) {
            throw new DukeException("Failed to load from disk");
        }
    }

    /**
     * Save task data to local storage
     * @param tasks list of String representing the TaskList
     * @throws DukeException if IOException is thrown
     */
    public void save(List<String> tasks) throws DukeException {
        try {
            Files.write(path, tasks);
        } catch (IOException e) {
            throw new DukeException("Failed to save to disk");
        }
    }
}
