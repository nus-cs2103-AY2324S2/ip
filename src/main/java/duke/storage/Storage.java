package duke.storage;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exceptions.DukeIoException;
/**
 * The Storage class handles file storage operations for the Duke chatbot application.
 */
public class Storage {

    private final String path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path The path to the file.
     * @throws DukeIoException If an error occurs during file creation.
     */
    public Storage(String path) {
        this.path = path;
        File f = new File(path);

        if (f.exists()) {
            return;
        }

        try {
            File parentPath = f.getParentFile();
            parentPath.mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeIoException("Error creating file!");
        }
    }

    /**
     * Loads data from the file.
     *
     * @return The data read from the file.
     * @throws DukeIoException If an error occurs while reading the file.
     */
    public String loadFileData() {
        Path p = Paths.get(path);
        assert Files.exists(p) : "File for storage should exist at this path!";
        try {
            return Files.readString(p);
        } catch (IOException e) {
            throw new DukeIoException("Error locating file");
        }
    }

    /**
     * Saves data to the file.
     *
     * @param dataToSave The data to be saved to the file.
     * @throws DukeIoException If an error occurs while writing to the file.
     */
    public void saveToFile(String dataToSave) {
        Path p = Paths.get(path);
        assert Files.exists(p) : "File for storage should exist at this path!";
        try {
            Files.writeString(p, dataToSave);
        } catch (IOException e) {
            throw new DukeIoException("Error saving to file");
        }
    }
}
