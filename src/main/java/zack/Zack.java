package zack;

import zack.commands.Command;
import zack.util.Parser;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

import java.io.IOException;

/**
 * The main class of the Zack program that handles user interactions and task management.
 */
public class Zack {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Zack instance with the specified file path for data storage.
     *
     * @param filePath The file path where task data is stored.
     */
    public Zack(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadedTasks(tasks);
        } catch (ZackException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Enumeration representing different types of tasks.
     */
    public enum TaskType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE, DATE, FIND
    }

    /**
     * Runs the Zack program, handling user input and task execution.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ZackException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showNewLineLine();
            }
        }
    }

    /**
     * The main method to start the Zack program.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        new Zack("data/tasks.txt").run();
    }
}
