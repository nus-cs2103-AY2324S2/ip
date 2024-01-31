package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The Duke class represents the main class for the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath The file path for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(tasks, storage, scanner, true);

        while (parser.getRunningStatus()) {
            String input = scanner.nextLine().trim();
            parser.setCommand(input);
            try {
                parser.processCommand();
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }

        }
        scanner.close();
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}