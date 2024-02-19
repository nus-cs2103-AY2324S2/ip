package chrisPBacon;

import java.io.FileNotFoundException;

import task.TaskList;
import util.Storage;
import util.Ui;
import util.UserInput;

/**
 *  This class contains the main method for the chatbot, ChrisP Bacon.
 *  ChrisP Bacon is a chatbot that manages the user's list of tasks.
 */
public class ChrisPBacon {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initialises new ui and storage objects, loads tasks into a new task list object.
     */
    public ChrisPBacon() {
        storage = new Storage("data/list.txt");
        ui = new Ui(this.storage);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            ui.printError("Oink! File not found :(\n");
            tasks = new TaskList();
        }
    }

    public String getGreeting() {
        return ui.printIntro();
    }

    public String getResponse(String input) {
        UserInput userInput = new UserInput(input);
        return userInput.processInput(ui, this.tasks);
    }


}
