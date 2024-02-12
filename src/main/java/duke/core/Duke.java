package duke.core;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `Duke` class represents the main application that manages tasks.
 * It handles user interactions, parses commands, and performs operations on tasks.
 */
public class Duke {
    /**
     * The file path where tasks are stored.
     */
    private static final String FILEPATH = "./data/duke.txt";
    /**
     * Represents the storage component responsible for loading and saving tasks.
     */
    private final Storage storage;
    /**
     * Represents the list of tasks managed by Duke.
     */
    private TaskList tasks;
    /**
     * Represents the user interface component for interacting with the user.
     */
    private final Ui ui;
    /**
     * Constructs a `Duke` instance with the specified file path for storage.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILEPATH);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Enumeration representing valid commands for the Duke application.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND
    }
    /**
     * Main loop for processing user commands and executing corresponding actions.
     */
    public void echo() {
        while (ui.hasNext()) {
            String msg = ui.getInput();
            Parser parser = new Parser(msg);
            try {
                if (msg.toUpperCase().startsWith(Command.BYE.name()) && parser.parseBye()) {
                    ui.formatReply(ui.exit());
                    break;
                } else if (msg.toUpperCase().startsWith(Command.LIST.name()) && parser.parseList()) {
                    ui.formatReply(ui.showAllTasks(tasks.getTasks()));
                } else if (msg.toUpperCase().startsWith(Command.FIND.name())) {
                    String description = parser.parseFind();
                    ui.formatReply(ui.showFoundTasks(tasks.findTasks(description)));
                } else if (msg.toUpperCase().startsWith(Command.MARK.name())) {
                    int taskIndex = parser.parseMark();
                    Task t = tasks.markTask(taskIndex);
                    ui.formatReply(ui.showMarkTaskMessage(t));
                    tasks.saveToStorage(storage);
                } else if (msg.toUpperCase().startsWith(Command.UNMARK.name())) {
                    int taskIndex = parser.parseUnmark();
                    Task t = tasks.unmarkTask(taskIndex);
                    ui.formatReply(ui.showUnmarkTaskMessage(t));
                    tasks.saveToStorage(storage);
                } else if (msg.toUpperCase().startsWith(Command.DELETE.name())) {
                    int taskIndex = parser.parseDelete();
                    Task t = tasks.deleteTask(taskIndex);
                    ui.formatReply(ui.showDeleteTaskMessage(t, tasks.getTasks().size()));
                    tasks.saveToStorage(storage);
                } else if (msg.toUpperCase().startsWith(Duke.Command.TODO.name())) {
                    Task t = parser.parseTodo();
                    tasks.addTask(t);
                    ui.formatReply(ui.showAddTaskMessage(t, tasks.getTasks().size()));
                    tasks.saveToStorage(storage);
                } else if (msg.toUpperCase().startsWith(Command.DEADLINE.name())) {
                    Task t = parser.parseDeadline();
                    tasks.addTask(t);
                    ui.formatReply(ui.showAddTaskMessage(t, tasks.getTasks().size()));
                    tasks.saveToStorage(storage);
                } else if (msg.toUpperCase().startsWith(Command.EVENT.name())) {
                    Task t = parser.parseEvent();
                    tasks.addTask(t);
                    ui.formatReply(ui.showAddTaskMessage(t, tasks.getTasks().size()));
                    tasks.saveToStorage(storage);
                } else {
                    ui.displayError("Unable to understand the command. Please enter a valid command.");
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
        ui.formatReply(ui.greetUser());
        this.echo();
    }
    /**
     * Main method to launch the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
