package friday.main;

import java.io.IOException;

import friday.parser.Parser;
import friday.storage.Storage;
import friday.task.TaskList;
import friday.task.Todo;
import friday.task.Deadline;
import friday.task.Event;
import friday.ui.Ui;

/**
 * The main class for the Friday task management application.
 * Friday manages tasks such as Todos, Deadlines, and Events, providing a simple command-line interface for users.
 * Tasks are stored in a data file, and users can interact with Friday by entering commands.
 */
public class Friday {
    private static final String DATA_FILE_PATH = "./src/main/java/data/Friday.txt";
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructs a Friday object with necessary components like UI, Storage, and Parser.
     * Initializes the task list by loading data from a file or creating a new one if the file does not exist.
     */
    public Friday() {
        ui = new Ui();
        storage = new Storage(DATA_FILE_PATH);
        parser = new Parser();
        try {
            storage.checkFile();
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }

        try {
            tasks = storage.loadDataFromFile();
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    /**
     * Runs the Friday application, interacting with the user through the command-line interface.
     */
    public void run() {
        ui.displayMessage(HORIZONTAL_LINE);
        ui.displayMessage("Hello! I'm Friday");
        ui.displayMessage("What can I do for you?");
        ui.displayMessage(HORIZONTAL_LINE);

        String userInput = ui.getUserInput().trim();
        String category = parser.parseCommand(userInput);
        while (!userInput.equals("bye")) {
            ui.displayMessage(HORIZONTAL_LINE);
            switch (category) {
            case "list":
                ui.displayMessage("Here are the tasks in your list:");
                ui.displayTaskList(tasks);
                break;
            case "mark":
                tasks.markTask(userInput);
                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    ui.displayMessage(e.getMessage());
                }
                break;
            case "unmark":
                tasks.unmarkTask(userInput);
                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
                Todo t = tasks.addTodo(userInput);
                try {
                    storage.appendToFile(t.toString() + System.lineSeparator());
                } catch (IOException e) {
                    ui.displayMessage(e.getMessage());
                }
                break;
            case "deadline":
                Deadline d = tasks.addDeadline(userInput);
                try {
                    storage.appendToFile(d.toString() + System.lineSeparator());
                } catch (IOException e) {
                    ui.displayMessage(e.getMessage());
                }
                break;
            case "event":
                Event e = tasks.addEvent(userInput);
                try {
                    storage.appendToFile(e.toString() + System.lineSeparator());
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
                break;
            case "delete":
                tasks.deleteTask(userInput);
                try {
                    storage.writeToFile(tasks);
                } catch (IOException err) {
                    ui.displayMessage(err.getMessage());
                }
                break;
            case "find":
                tasks.searchTask(userInput);
                break;
            default:
                ui.displayMessage("HUH? What do you mean?");
                break;
            }
            ui.displayMessage(HORIZONTAL_LINE);
            userInput = ui.getUserInput().trim();
            category = userInput.split(" ")[0];
        }
        ui.displayMessage(HORIZONTAL_LINE);
        ui.displayMessage("Bye. Hope to see you again soon!");
        ui.displayMessage(HORIZONTAL_LINE);
    }

    /**
     * The entry point of the Friday application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Friday().run();
    }
}