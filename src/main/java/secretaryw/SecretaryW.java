package secretaryw;

import java.util.ArrayList;

/**
 * Main class for the SecretaryW application.
 */
public class SecretaryW {
    private Parser parser;
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a new SecretaryW object.
     */
    public SecretaryW() {
        storage = new Storage();
        ui = new Ui();
        ArrayList<Task> tasks = storage.loadTasksFromFile();
        taskList = new TaskList(tasks);
        parser = new Parser(ui, taskList);
    }

    public String getResponse(String input) {
        try{
            String response = parser.handleCommand(input.trim().split("\\s+", 2));
            storage.saveTasksToFile(taskList.getTasks());
            return response;
        } catch (Exception e) {
            return "OOPS!!! " + e.getMessage();
        }
    }
}
