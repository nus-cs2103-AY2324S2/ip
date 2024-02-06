package chatbot;

import chatbot.exception.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Encapsulates the data and behaviour of Fatnom.
 * Fatnom is a chatbot application designed to help users manage their task lists.
 * It provides functionalities to add, delete, mark, unmark, list and find tasks.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Duke {
    private static final String NAME = "Fatnom";
    private static Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke object.
     *
     * @param filepath The file path of the storage file containing task data.
     * @throws DukeException For initialisation errors while loading data from storage.
     */
    public Duke(String filepath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            Ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Fatnom application.
     * Initialises the necessary components, displays a welcome message,
     * loads data from storage (if any), and continuously reads and
     * processes user input until the user exits the application.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Ui.printWelcomeMessage(NAME);
        Parser parser = new Parser(tasks, ui);
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = sc.nextLine();
                isExit = parser.parseUserInput(input);
            } catch (DukeException e) {
                Ui.printErrorMessage(e.getMessage());
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                String exceptionMessage = Ui.createLine() + "\n"
                        + "invalid date time format! please use YYYY-MM-DD HH:MM format!" + "\n"
                        + Ui.createLine();
                Ui.printErrorMessage(exceptionMessage);
            }
        }
    }

    /**
     * Starts the Fatnom application.
     * Initialises a Duke object and runs the application.
     *
     * @param args The command-line arguments.
     * @throws DukeException For initialisation errors.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }
}