package duke.core;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke class represents the main application that manages tasks.
 * It handles user interactions, parses commands, and performs operations on tasks.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Enumeration representing valid commands for Duke application.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    /**
     * Constructs a Duke instance with the specified file path for storage.
     *
     * @param filePath The file path for storage.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e){
            ui.displayError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Main loop for processing user commands and executing corresponding actions.
     */
    public void echo() {

        while (ui.next()) {
            String msg = ui.getInput();
            Parser parser = new Parser(msg);

            try {
                if (msg.equalsIgnoreCase(Command.BYE.name())) {

                    ui.exit();
                    break;

                } else if (msg.equalsIgnoreCase(Command.LIST.name())) {

                    ui.formatReply(tasks.printList());

                } else if (msg.toUpperCase().startsWith(Command.MARK.name())){

                    int taskIndex = parser.parseMark();
                    ui.formatReply(tasks.markTask(taskIndex));
                    tasks.saveToStorage(storage);

                } else if (msg.toUpperCase().startsWith(Command.UNMARK.name())){

                    int taskIndex = parser.parseUnMark();
                    ui.formatReply(tasks.unmarkTask(taskIndex));
                    tasks.saveToStorage(storage);

                } else if (msg.toUpperCase().startsWith(Command.DELETE.name())) {

                    int taskIndex = parser.parseDelete();
                    ui.formatReply(tasks.deleteTask(taskIndex));
                    tasks.saveToStorage(storage);

                } else {

                    Task t = parser.parseAdd();
                    ui.formatReply(tasks.addTask(t));
                    tasks.saveToStorage(storage);

                }
            } catch (DukeException e) {

                ui.displayError(e.getMessage());

            }
        }

        ui.closeScanner();

    }

    /**
     * Initializes the Duke application, greets the user, and starts command processing.
     */
    public void run() {
        ui.greetUser();
        this.echo();
    }

    /**
     * Main method to launch the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
