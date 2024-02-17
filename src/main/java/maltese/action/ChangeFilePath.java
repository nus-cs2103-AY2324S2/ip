package maltese.action;

import maltese.Storage;
import maltese.action.TaskList;
import maltese.exception.MalteseException;

import java.io.FileNotFoundException;

public class ChangeFilePath implements Action {
    private String filePath;
    public TaskList tasks;


    public ChangeFilePath(String filePath, Storage storage) {
        this.filePath = filePath;
        storage.changeFile(filePath);
        try {
            this.tasks = storage.loadFromFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ChangeFilePath parse(String command, Storage storage) throws MalteseException {
        String[] words = command.split(" ");
        String filePath = words[1];
        // HANDLE ERROR WHEN WORDS.LENGTH != 2
        return new ChangeFilePath(filePath, storage);
    }


    @Override
    public String response() {
        return "Changing file path to " + filePath + "\n";
    }


}