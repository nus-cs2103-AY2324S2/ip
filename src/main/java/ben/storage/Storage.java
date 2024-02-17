package ben.storage;

import ben.exceptions.BenException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handles the reading and writing of tasks to a file for persistent storage.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the given text to the specified file.
     *
     * @param filePath  The file path to write to.
     * @param textToAdd The text to be written to the file.
     * @throws IOException If an I/O error occurs during writing.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(textToAdd);
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return The File object representing the loaded file.
     * @throws BenException          If there is an issue with the file or folder paths.
     * @throws FileNotFoundException If the file specified by the file path does not exist.
     */
    public File load() throws BenException, FileNotFoundException {
        // hard-coded
        String targetFolder = "data";

        if (!Files.exists(Paths.get(targetFolder))) {
            throw new BenException("./data/ folder path does not exist.");
        }

        // File does not exist
        if (!Files.exists(Paths.get(this.filePath))) {
            throw new FileNotFoundException("Target file does not exist.");
        }

        return new File(this.filePath);
    }

    /**
     * Saves tasks to the file.
     *
     * @param saveInput The string representation of tasks to be saved.
     * @throws BenException If there is an issue with the file or folder paths.
     */
    public void save(String saveInput) throws BenException {
        String targetFolder = "data";

        if (!Files.exists(Paths.get(targetFolder))) {
            throw new BenException("./data/ folder path does not exist.");
        }

        // File does not exist
        if (!Files.exists(Paths.get(this.filePath))) {
            throw new BenException("Target file does not exist.");
        }

        try {
            writeToFile(this.filePath, saveInput);
        } catch (IOException e) {
            throw new BenException("Something went wrong: " + e.getMessage());
        }
    }
}
