package osiris.storage;
import java.io.File;

/**
 * Manages file storage for the Osiris.
 */
public class Storage {

    private final String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path for storage.
     */
    public Storage(String filepath) {
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
     */
    public boolean storageFileExist() {
        try {
            File file = new File(this.filepath);
            return file.exists();
        } catch (SecurityException e) {
            System.err.println("SecurityException occurred." );
            return false;
        } catch (NullPointerException e) {
            System.err.println("NullPointerException occurred.");
            return false;
        } catch (Exception e) {
            System.err.println("An error occurred with Function storageFileExist().");
            return false;
        }
    }
}
