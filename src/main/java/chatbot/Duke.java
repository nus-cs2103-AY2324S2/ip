package chatbot;

import chatbot.exception.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Encapsulates the main class of the chatbot application.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Duke {
    private static final String NAME = "Fatnom";
    private static final String FILE_PATH = "data/tasks.txt";

    private static Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Duke instance.
     * Initialises the Ui, Storage, TaskList and Parser objects.
     * Loads the existing TaskList from the saved file if available, otherwise, initialises an empty one.
     *
     * @throws DukeException For initialisation errors.
     */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            Ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui);
    }

    /**
     * Retrieves the Parser object associated with this Duke instance.
     *
     * @return The Parser object.
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * Retrieves the TaskList object associated with this Duke instance.
     *
     * @return The TaskList object.
     */
    public TaskList getTasklist() {
        return tasks;
    }

    /**
     * Retrieves the Ui object associated with this Duke instance.
     *
     * @return The Ui object.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Starts the Fatnom application.
     * Initialises a Duke object and runs the application by continuously reading and parsing user input
     * until the application is terminated.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Ui.printWelcomeMessage(NAME);
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = sc.nextLine();
                parser.parseUserInput(input);
            } catch (DukeException e) {
                Ui.printErrorMessage(e.getMessage());
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                String exceptionMessage = "invalid date time format! please use YYYY-MM-DD HH:MM format!";
                Ui.printErrorMessage(exceptionMessage);
            }
        }
    }

    /**
     * Serves as the entry point for the Fatnom application.
     * Creates a new Duke instance and invokes the run method.
     *
     * @param args The command-line arguments.
     * @throws DukeException For initialisation errors.
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}