package chrisPBacon;

import task.TaskList;

import java.io.FileNotFoundException;

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
     *
     * @param filePath of the saved task list
     */
    public ChrisPBacon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printError("Oink! File not found :(\n");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot program.
     */
    public void run() {
        ui.printIntro();

        UserInput userInput = new UserInput();
        while (!userInput.isInputBye()) {
            userInput.processInput(ui, this.tasks);
        }
        // if user entered "bye", save list and exit chatbot.
        ui.printBye();
        storage.save(this.tasks);
    }
}
