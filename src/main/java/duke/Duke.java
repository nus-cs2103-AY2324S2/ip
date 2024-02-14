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

    private static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for storage.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
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
    public String run(String userInput) {
        String response = "";
        try {
            response += ui.showLine();
            Command c = Parser.parse(userInput);
            response += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response += ui.showError(e.getMessage());
        } finally {
            response += ui.showLine();
        }
        return response;
    }

    /**
     * A method to show the welcome message.
     *
     * @return A welcome message.
     */
    public String showGreetings() {
        return ui.showWelcomeMsg();
    }
}
