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
    private static final String FILE_PATH = "data/tasks.txt";

    private static Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public Parser getParser() {
        return parser;
    }

    public TaskList getTasklist() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

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
     * Starts the Fatnom application.
     * Initialises a Duke object and runs the application.
     *
     * @param args The command-line arguments.
     * @throws DukeException For initialisation errors.
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}