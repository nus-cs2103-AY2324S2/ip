package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;


/**
 * Represents a chatbot of the name Duke.
 */
public class Duke {
    private TaskList list;
    private Ui ui;
    private Storage storage;

    /**
     * Default Constructor for duke setting default filepath of task list file to data/duke.txt.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            list = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
            list = new TaskList();
        }
    }

    /**
     * Creates a Duke object that can run.
     *
     * @param filePath a filepath where Duke stores a {@link TaskList} in
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
            list = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        boolean end = false;
        while (!end) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                c.execute(list, ui, storage);
                end = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        assert end == true;
    }


    /**
     * Gets a response upon an input from the user.
     *
     * @param input the user input
     * @return response to the user
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(list, ui, storage);
            return ui.craftResponse();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return ui.craftResponse();
        }
    }

    /**
     * Duke greets user with introduction.
     *
     * @return the greeting output
     */
    public String greet() {
        this.ui.greet();
        return ui.craftResponse();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

