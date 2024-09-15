package eve;

import java.util.ArrayList;

import eve.filestorage.Storage;
import eve.parser.Commands;
import eve.parser.Parser;
import eve.tasks.Task;

/**
 * main class of the chatbot
 */
public class Eve {
    private Storage storage;
    private ArrayList<Task> tasks;
    private Parser parser;

    /**
     * Constructor for the Eve class
     */
    public Eve() {
        this.storage = new Storage();

        try {
            tasks = storage.loadFileContents();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.parser = new Parser(this);
    }

    public String sayHello() {
        return Commands.commandHello();
    }


    public String getResponse(String input) {
        try {
            String response = parser.parseAndExecute(input);
            //save tasks to file
            Storage.writeToFile(tasks);
            return response;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public ArrayList<Task> getTaskList() {
        assert tasks != null : "Task list is empty";
        return tasks;
    }
}
