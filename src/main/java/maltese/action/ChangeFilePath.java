package maltese.action;

import java.io.IOException;

import maltese.Storage;
import maltese.exception.InvalidFilePathException;
import maltese.exception.MalteseException;
import maltese.exception.NoFilePathException;


public class ChangeFilePath implements Action {
    private String filePath;
    public TaskList tasks;

    private static final String FILE_PATH_EMPTY_MESSAGE = "File path cannot be empty";
    private static final String CHANGE_FILE_PATH_RESPONSE = "Changing file path to ";


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


    @Override
    public String getResponse() {
        return CHANGE_FILE_PATH_RESPONSE + filePath + "\n";
    }

}
