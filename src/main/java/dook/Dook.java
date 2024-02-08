package dook;

import command.Command;
import task.TaskList;
import java.util.ArrayList;
import java.util.List;


public class Dook {

    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "dook.txt"));
    private TaskList tasks;
    private Storage storage;

    public Dook() {
        this.tasks = new TaskList();
        this.storage = new Storage(FILE_PATH);
    }

    public String getResponse(String input) {
        try {
            this.tasks = this.storage.loadTaskListFromFile();
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
