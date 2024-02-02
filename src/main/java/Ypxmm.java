import java.util.ArrayList;

import Commands.Command;

import Parsing.Parser;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

import Exceptions.YpxmmException;

/**
 * Main class for the Ypxmm application.
 */
public class Ypxmm {
    /** The storage for tasks. */
    private Storage storage;
    /** The task list. */
    private TaskList tasks;
    /** The user interface. */
    private Ui ui;

    /**
     * Constructs a Ypxmm object.
     *
     * @param filePath the file path for storage
     */
    public Ypxmm(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksIntoTaskList());
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    /**
     * Runs the Ypxmm application.
     */
    public void run() {
        ui.sayHello();
        ui.showLine();
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                ArrayList<String> parsed = Parser.parse(input);
                Command command = Command.valueOf(parsed.get(0).toUpperCase());
                command.execute(tasks, ui, storage, parsed);
                ui.showLine();
                if (command.equals(Command.BYE)) {
                    isRunning = false;
                }
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
                ui.showLine();
            }
        }
    }

    /**
     * Main method to start the Ypxmm application.
     *
     * @param args command line arguments
     * @throws YpxmmException if an error occurs in the Ypxmm application
     */
    public static void main(String[] args) throws YpxmmException {
        new Ypxmm("/data/Ypxmm.txt").run();
    }
}