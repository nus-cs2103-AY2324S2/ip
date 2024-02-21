package duke.storage;

import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents the file used to store address book data.
 */
public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "data.txt";
    public final Path file;
    String currentDir = System.getProperty("user.dir");
    Path currentPath = Paths.get(currentDir);
    Path dataPath = currentPath.resolve("data");

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String file) throws InvalidStorageFilePathException {
        this.file = dataPath.resolve(file);
        if (!isValidPath(this.file)) {
            throw new InvalidStorageFilePathException("OOPS!! detective.storage.Storage file should end with '.txt'");
        }
    }

    /**
     * Save the data to the storage file.
     *
     * @param task the task should be stored.
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(ArrayList<Task> task) throws StorageOperationException {
        try {
            ArrayList<String> encodedTask = TaskEncoder.encodeTask(task);
            Files.write(file, encodedTask);
        } catch (IOException e) {
            throw new StorageOperationException("OOPS!! Error writing to file: " + file);
        }
    }

    /**
     * Loads the data from this storage file, and then returns it.
     *
     * @return creates the directory and data.txt then returns an empty if the file does not exist.
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public ArrayList<Task> load() throws StorageOperationException {
        if (!Files.exists(dataPath)) {
            try {
                Files.createDirectories(dataPath);
                Files.createFile(file);
                System.out.println("The data folder and data.txt has been created. >v<");
                return new ArrayList<>();
            } catch (IOException e) {
                throw new StorageOperationException("OOPS!! Error creating the file: " + file);
            }
        } else if (!Files.exists(file)) {
            try {
                Files.createFile(file);
                System.out.println("The and data.txt has been created. >v<");
                return new ArrayList<>();
            } catch (IOException e) {
                throw new StorageOperationException("OOPS!! Error creating the file: " + file);
            }
        } else {
            try {
                return TaskDecoder.decodeTask(Files.readAllLines(file));
            } catch (IOException e) {
                throw new StorageOperationException("OOPS!! Error reading the file: " + file);
            }
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends Exception {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occurred while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
