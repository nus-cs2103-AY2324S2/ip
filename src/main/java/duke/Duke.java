package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for storage.
     *
     * @param filePath The file path for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program, processing user commands until the "bye" command is entered.
     * The user is prompted with a welcome message and can interact with the chatbot.
     * Recognized commands include list, mark, unmark, todo, deadline, event, and more.
     * Further carry out the following functions based on the command entered.
     */
    public void run() {
        ui.welcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}

