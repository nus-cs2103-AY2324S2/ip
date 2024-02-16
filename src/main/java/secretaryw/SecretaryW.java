package secretaryw;

import java.io.*;
import java.util.ArrayList;

/**
 * Main class for the SecretaryW application.
 */
public class SecretaryW {
    private static final String FILE_PATH = "./data/SecretaryW.txt";
    private static final String line = "-----------------------------------------------------------------\n";
    private Parser parser;
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a new SecretaryW object.
     */
    public SecretaryW() {
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        try {
            ArrayList<Task> tasks = storage.loadTasksFromFile();
            this.taskList = new TaskList(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try{
            String[] command = parser.getNextCommand();
            String response = parser.handleCommand(command);
            storage.saveTasksToFile(taskList.getTasks());
            return response;
        } catch (Exception e) {
            return "OOPS!!! " + e.getMessage();
        }
    }
}
