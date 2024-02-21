package maltese.action;

import maltese.Storage;
import maltese.exception.InvalidFilePathException;
import maltese.exception.MalteseException;
import maltese.exception.NoFilePathException;

import java.io.IOException;

public class ChangeFilePath implements Action {
    private String filePath;
    public TaskList tasks;
    public boolean isGuide;


    public ChangeFilePath(String filePath, Storage storage, boolean isGuide) throws IOException {
        this.filePath = filePath;
        assert filePath.length() > 0 : "File path cannot be empty";
        if (!storage.fileExists(filePath)) {
            // If the file doesn't exist, create it
            storage.createFile(filePath);
        }
        if (!isGuide) {
            storage.changeFile(filePath);
            this.tasks = storage.loadFromFile();
        } else {
            this.tasks = storage.loadFromFile(); // Load tasks from the default file path
            storage.changeFile(filePath);
        }
        this.isGuide = isGuide;
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
        return new ChangeFilePath(filePath, storage, false);
    }


    @Override
    public String getResponse() {
        if (isGuide) {
            return "I loaded some sample data! Type 'list' to see the sample data";
        }
        return "Changing file path to " + filePath + "\n";
    }

}