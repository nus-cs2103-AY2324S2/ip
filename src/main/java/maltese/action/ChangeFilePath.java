package maltese.action;

import java.io.IOException;

import maltese.Storage;
import maltese.exception.InvalidFilePathException;
import maltese.exception.MalteseException;
import maltese.exception.NoFilePathException;

/**
 * Represents an action to change the file path.
 */
public class ChangeFilePath implements Action {

    private static final String CHANGE_FILE_PATH_RESPONSE = "Changing file path to ";
    private static final String FILE_PATH_EMPTY_MESSAGE = "File path cannot be empty";
    private String filePath;
    private TaskList tasks;

    /**
     * Constructs a ChangeFilePath action with the specified file path and storage.
     *
     * @param filePath The new file path to change to.
     * @param storage  The storage instance to interact with file operations.
     * @throws IOException If an I/O error occurs.
     */
    public ChangeFilePath(String filePath, Storage storage) throws IOException {
        this.filePath = filePath;
        assert filePath.length() > 0 : FILE_PATH_EMPTY_MESSAGE;
        if (!storage.fileExists(filePath)) {
            // If the file doesn't exist, create it
            storage.createFile(filePath);
        }
        this.tasks = storage.loadFromFile(); // Load tasks from the default file path
        storage.changeFile(filePath);
    }

    /**
     * Parses the command to change the file path.
     *
     * @param command The command containing the new file path.
     * @param storage The storage instance to interact with file operations.
     * @return A ChangeFilePath action with the specified file path.
     * @throws MalteseException If there is an error parsing the command.
     * @throws IOException      If an I/O error occurs.
     */
    public static ChangeFilePath parse(String command, Storage storage) throws MalteseException, IOException {
        String[] words = command.split(" ");
        if (words.length < 2) {
            throw new NoFilePathException();
        }
        if (words.length > 2) {
            throw new InvalidFilePathException();
        }
        String filePath = words[1];
        return new ChangeFilePath(filePath, storage);
    }

    /**
     * Gets the response message after changing the file path.
     *
     * @return The response message indicating the file path change.
     */
    @Override
    public String getResponse() {
        return CHANGE_FILE_PATH_RESPONSE + filePath + "\n";
    }

}
