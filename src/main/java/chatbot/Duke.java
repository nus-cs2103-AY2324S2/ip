package chatbot;

import chatbot.exception.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Encapsulates the data and behaviour of the chatbot.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Duke {
    private static final String NAME = "Fatnom";
    private static Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }
}