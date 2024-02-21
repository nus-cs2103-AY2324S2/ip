package seedu.chatteroo;

import seedu.chatteroo.commands.Command;
import seedu.chatteroo.parser.Parser;
import seedu.chatteroo.storage.Storage;
import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;

import java.io.IOException;

/**
 * The main class of the Chatteroo ChatBot program.
 */
public class Chatteroo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for the Chatteroo class.
     * @throws IOException If an I/O error occurs.
     */
    public Chatteroo() throws Exception {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.loadTasks());

        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert tasks != null : "TaskList should not be null";
    }


    /**
     * Runs the Chatteroo program and handles user input.
     * @throws IOException If an I/O error occurs.
     */
    public String run(String input) throws IOException {
        String response = "";

        try {
            Command c = Parser.parseInput(input);
            response = c.execute(tasks, ui, storage);
        } catch (ChatterooException e) {
                return ui.showErrorResponse(e);
        }
        storage.saveTasks(tasks);
        return response;
    }

}




