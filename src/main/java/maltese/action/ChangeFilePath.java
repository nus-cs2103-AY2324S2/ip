package maltese.action;

import maltese.Storage;
import maltese.exception.InvalidFilePathException;
import maltese.exception.MalteseException;
import maltese.exception.NoFilePathException;

import java.io.IOException;

public class ChangeFilePath implements Action {
    private String filePath;
    public TaskList tasks;


    public ChangeFilePath(String filePath, Storage storage) throws IOException {
        this.filePath = filePath;
        assert filePath.length() > 0 : "File path cannot be empty";
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
        return "Changing file path to " + filePath + "\n";
    }

}