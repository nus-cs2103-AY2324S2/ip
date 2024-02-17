package cat;

import java.io.FileNotFoundException;
import java.io.IOException;

import cat.command.Command;
import cat.ui.Ui;
import cat.ui.response.Response;

/**
 * The main class of the Duke program.
 */
public class Cat {
    private static final String FILE_NAME = "duke.state";

    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * The constructor for the Duke class.
     *
     * @param fileName file to store state to
     */
    public Cat(String fileName) {
        assert fileName != null : "File name must not be null";

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
    public Cat() {
        this(FILE_NAME);
    }

    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks);
        } catch (Parser.InvalidCommandType e) {
            return Ui.showCommandNotFound(e.getCommand());
        } catch (Parser.InvalidCommandData e) {
            return Ui.showError(e);
        }
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
