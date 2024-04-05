package alfred;

import alfred.task.TaskException;
import alfred.task.TaskList;
import alfred.util.FileManager;
import alfred.util.Parser;
import alfred.util.Ui;

import java.util.ArrayList;


public class Alfred {
    private String filePath = "./data/saveData.txt";
    private FileManager storage;  // This is the storage class just named duke.util.FileManager
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Alfred() {
        this.filePath = "./data/saveData.txt";
        this.storage = new FileManager(filePath);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (Exception e) {
            tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks, this.ui);
    }

    public ArrayList<String> getResponse(String input) {
        try {
            ArrayList<String> s = parser.read(input);
            storage.saveFile(tasks.giveList());
            return s;
        } catch (TaskException e) {
            throw new RuntimeException(e);
        }
    }
}
