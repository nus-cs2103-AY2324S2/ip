package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.ui.Ui;

/**
 * The main class of the Duke program.
 */
public class Duke {
    private static final String FILE_NAME = "duke.state";

    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * The constructor for the Duke class.
     *
     * @param fileName file to store state to
     */
    public Duke(String fileName) {
        storage = new Storage(fileName);
        ui = new Ui();
        try {
            tasks = storage.readTaskList();
        } catch (IOException | ClassNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Default constructor to launch from JavaFX.
     */
    public Duke() {
        this(FILE_NAME);
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (Parser.InvalidCommandType e) {
            ui.showCommandNotFound(e.getCommand());
        } catch (Parser.InvalidCommandData e) {
            ui.showError(e);
        }
        return ui.flush();
    }

    /**
     * Saves the state to storage.
     */
    public void save() {
        try {
            storage.writeTaskList(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find state file \"" + FILE_NAME + "\"");
        } catch (IOException e) {
            System.out.println("Cannot write to state file \"" + FILE_NAME + "\"");
        }
    }
}
