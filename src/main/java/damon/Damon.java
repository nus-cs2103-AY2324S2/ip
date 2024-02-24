package damon;

import damon.command.Command;
import damon.exceptions.DamonExceptions;
import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;
import damon.parser.Parser;

/**
 * Creates a chatbot called Damon. A Damon object responds to user's input
 * by adding, deleting, marking, and un-marking Tasks in TaskList object.
 * Damon also store Tasks in TaskList in storage file of its filepath using Storage object.
 */
public class Damon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Damon object with String filePath parameter.
     *
     * @param filePath Path of storage file of this Damon object.
     */
    public Damon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DamonExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Runs chatbot Damon.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DamonExceptions e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Damon("..\\Damon.txt").run();
    }
}

