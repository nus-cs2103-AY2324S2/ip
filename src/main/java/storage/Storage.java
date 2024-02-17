package storage;

import exceptions.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents the interface for managing data transfer between the program and the local storage.
 * Assumes that input path has preceding "./data" and proceeded by ".txt", Parser to check for condition.
 */
public class Storage {
    private String  pathString = "./data/data.txt";
    private Path path;

    public Storage() {
        this.path = Paths.get(pathString);
    }

    public Storage(String pathString) {
        this.pathString = pathString;
        this.path = Paths.get(pathString);
    }

    public String getCurrentPath() {
        return this.pathString;
    }

    public void switchPath(String pathString) {
        this.pathString = pathString;
        this.path = Paths.get(pathString);
    }

    public boolean fileExists() {
        return Files.exists(this.path);
    }

    public boolean parentDirectoryExists() {
        Path parent = path.getParent();
        return Files.isDirectory(parent);
    }

    public void createFile() throws IOException {
        Files.createFile(this.path);
    }

    public void createParentDirectories() throws IOException {
        Files.createDirectories(this.path.getParent());
    }

    public void createPathIfMissing() throws IOException {
        if (!parentDirectoryExists()) {
            createParentDirectories();
        }
        if (!fileExists()) {
            createFile();
        }
    }

    /**
     * Load task data from local storage
     * @return list of String representing the TaskList
     * @throws StorageException if IOException is thrown
     */
    public List<String> load() throws StorageException {
        try {
            createPathIfMissing();
            List<String> fileOutput = Files.readAllLines(this.path);
            return fileOutput;
        } catch (IOException e) {
            throw new StorageException("Failed to load from disk\n" + e.getMessage());
        }
    }

    /**
     * Save task data to local storage
     * @param tasks list of String representing the TaskList
     * @throws StorageException if IOException is thrown
     */
    public void save(List<String> tasks) throws StorageException {
        try {
            createPathIfMissing();
            Files.write(this.path, tasks);
        } catch (IOException e) {
            throw new StorageException("Failed to save to disk\n" + e.getMessage());
        }
    }

}
