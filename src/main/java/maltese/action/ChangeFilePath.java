package maltese.action;

import maltese.Storage;
import maltese.exception.MalteseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ChangeFilePath implements Action {
    private String filePath;
    public TaskList tasks;


    public ChangeFilePath(String filePath, Storage storage) throws IOException {
        this.filePath = filePath;
        if (!storage.fileExists(filePath)) {
            // If the file doesn't exist, create it
            storage.createFile(filePath);
        }
        // Load tasks without overwriting the existing file contents
        storage.changeFile(filePath);
        this.tasks = storage.loadFromFile();
    }



//    public ChangeFilePath(String filePath, Storage storage) throws FileNotFoundException {
//        this.filePath = filePath;
//        storage.changeFile(filePath);
//        this.tasks = storage.loadFromFile();// dont overwrite sample.txt
//    }

    public static ChangeFilePath parse(String command, Storage storage) throws MalteseException, IOException {
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