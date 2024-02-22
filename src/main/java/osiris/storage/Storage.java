package osiris.storage;

import java.io.File;

import osiris.exceptions.OsirisStorageFileException;

/**
 * Manages file storage for the Osiris.
 */
public class Storage {

    /** File path of the storage file. */
    private final String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path for storage.
     */
    public Storage(String filepath) {
        assert filepath != null && !filepath.isEmpty() : "Filepath must not be null or empty";
        this.filepath = filepath;
    }

    /**
     * Retrieves the file path.
     *
     * @return The file path.
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Checks if the storage file exists.
     *
     * @return True if the storage file exists, false otherwise.
     * @throws OsirisStorageFileException If error accessing file.
     */
    public boolean doesStorageFileExist() {
        try {
            File file = new File(filepath);
            return file.exists();
        } catch (SecurityException e) {
            System.err.println("SecurityException occurred.");
            throw new OsirisStorageFileException("Trouble accessing storage file: " + filepath);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException occurred.");
            throw new OsirisStorageFileException("Trouble accessing storage file: " + filepath);
        } catch (Exception e) {
            System.err.println("An error occurred with Function storageFileExist().");
            throw new OsirisStorageFileException("Trouble accessing storage file: " + filepath);
        }
    }
}
