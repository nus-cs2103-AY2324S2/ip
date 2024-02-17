package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import exceptions.StorageException;

/**
 * Represents the interface for managing data transfer between the program and the local storage.
 * Assumes that input path has preceding "./data" and proceeded by ".txt".
 */
public class Storage {
    protected String pathString = "./data/data.txt";
    protected Path path;

    public Storage() {
        this.path = Paths.get(pathString);
    }

    /**
     * Instantiates Storage object with the specified path.
     */
    public Storage(String pathString) {
        this.pathString = pathString;
        this.path = Paths.get(pathString);
        assert this.path.startsWith("./data");
        assert this.path.endsWith(".txt");
    }

    public String getCurrentPath() {
        return this.pathString;
    }

    /**
     * Changes the location to load/save data.
     *
     * @param pathString String of new path.
     */
    public void switchPath(String pathString) {
        this.pathString = pathString;
        this.path = Paths.get(pathString);
        assert this.path.startsWith("./data");
        assert this.path.endsWith(".txt");
    }

    /**
     * Returns true if the file located from the path exists locally.
     */
    public boolean fileExists() {
        return Files.exists(this.path);
    }

    /**
     * Returns true if the file located by the parent path exists and is a directory.
     */
    public boolean parentDirectoryExists() {
        Path parent = path.getParent();
        return Files.isDirectory(parent);
    }

    /**
     * Creates an empty file in the location specified by path.
     *
     * @throws IOException if an I/O error occurs or the parent directory does not exist.
     */
    public void createFile() throws IOException {
        Files.createFile(this.path);
    }

    /**
     * Creates all nonexistent parent directories.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void createParentDirectories() throws IOException {
        Files.createDirectories(this.path.getParent());
    }

    /**
     * Creates all necessary directories and file needed for loading/saving of data.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void createPathIfMissing() throws IOException {
        if (!parentDirectoryExists()) {
            createParentDirectories();
        }
        if (!fileExists()) {
            createFile();
        }
    }

    /**
     * Returns task data loaded from local storage.
     * Will attempt to create directories and files if missing.
     *
     * @return list of String representing the TaskList.
     * @throws StorageException if IOException is thrown.
     */
    public List<String> load() throws StorageException {
        try {
            createPathIfMissing();
            List<String> fileOutput = Files.readAllLines(this.path);
            return fileOutput;
        } catch (IOException e) {
            throw new StorageException("Failed to load from disk\n");
        }
    }

    /**
     * Saves task data to local storage.
     * Will attempt to create directories and files if missing.
     *
     * @param tasks list of String representing the TaskList.
     * @throws StorageException if IOException is thrown.
     */
    public void save(List<String> tasks) throws StorageException {
        try {
            createPathIfMissing();
            Files.write(this.path, tasks);
        } catch (IOException e) {
            throw new StorageException("Failed to save to disk\n");
        }
    }

    /**
     * Returns comparison of this object with any subclass of Object.
     *
     * @param o Object to be compared with.
     * @return true if both path and pathString are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Storage)) {
            return false;
        }
        Storage test = (Storage) o;
        if (!(this.pathString.equals(test.pathString))) {
            return false;
        }
        return this.path.toString().equals(test.path.toString());
    }
}
