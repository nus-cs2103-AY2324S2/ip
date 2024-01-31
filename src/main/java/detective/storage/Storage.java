package detective.storage;

import detective.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    public void save(ArrayList<Task> task) throws StorageOperationException {
        try {
            ArrayList<String> encodedTask = TaskEncoder.encodeTask(task);
            Files.write(file, encodedTask);
        } catch (IOException e) {
            throw new StorageOperationException("OOPS!! Error writing to file: " + file);
        }
    }

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

    public static class InvalidStorageFilePathException extends Exception {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
