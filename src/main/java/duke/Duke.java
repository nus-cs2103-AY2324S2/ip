package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * The Duke program is a chatbot that helps users to keep track of their tasks.
 * It can store and retrieve tasks from a file, and perform operations such as adding, deleting,
 * and marking tasks as done.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath The file path to store the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Runs the Duke program.
     */
    public void run() {
        Application.launch(Ui.class);
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.save(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to run the Duke program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
