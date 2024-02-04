package toothless;

import toothless.exception.ToothlessException;
import toothless.parser.Parser;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * The main class for the Toothless TaskList chatbot.
 */
public class Toothless {
    private boolean isRunning;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * The main method and entry point of the program. 
     * 
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Toothless toothless = new Toothless("./data/tasklist.txt");
        toothless.run();
    }

    /**
     * A private constructor to initialize the chatbot.
     * 
     * @param filepath A String indicating the filepath where data would be stored.
     */
    private Toothless(String filepath) {
        ui = new Ui();
        parser = new Parser();
        
        try {
            this.storage = new Storage(filepath);
            this.tasks = new TaskList(this.storage.loadStorage());
        } catch (ToothlessException e) {
            ui.printMessage(e.getMessage());
            ui.printMessage("Sorry, tasklist.txt is corrupted. Starting a blank tasklist.");
            this.tasks = new TaskList();
        }
    }

    /**
     * The main loop of the program.
     */
    private void run() {
        this.isRunning = true;
        ui.printWelcome();
        
        while (this.isRunning) {
            try {
                String userInput = ui.readCommand();
                ui.printLine();
                parser.parseInput(userInput, tasks, ui);
                this.isRunning = parser.isStillRunning();
            } catch (ToothlessException e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        
        try {
            storage.saveToStorage(tasks.getTasks());
            ui.printMessage("Successfully saved task data to tasklist.txt.");
        } catch (ToothlessException e) {
            ui.printMessage(e.getMessage());
        }
        
    }
}
