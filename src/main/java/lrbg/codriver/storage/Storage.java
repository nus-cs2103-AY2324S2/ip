package lrbg.codriver.storage;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a storage for saving and loading data.
 */
public class Storage {
    /** The file path of the storage. */
    private final String filePath;

    /**
     * Constructor for Storage.
     * @param filePath The file path of the storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the storage.
     * @return The data loaded from the storage.
     * @throws IOException If an I/O error occurs.
     */
    public String[] load() throws IOException {
        Path path = Paths.get(this.filePath);
        return Files.readString(path).split("\n");
    }

    /**
     * Saves the data to the storage.
     * @param saveString The data to be saved.
     * @return Whether the data is saved successfully.
     */
    public boolean save(String saveString) {
        try {
            // if the lrbg.codriver.data directory does not exist, create it
            Files.createDirectory(Paths.get("./data"));
        } catch (IOException e) {
            // do nothing
        }

        // if the file exists, delete the old file
        Path path = Paths.get(this.filePath);
        try {
            if (Files.exists(path)) {
                Files.delete(path);
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(saveString);
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
