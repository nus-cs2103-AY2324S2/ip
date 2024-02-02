import java.io.IOException;
import Exceptions.DukeException;

import Utility.TaskList;
import Utility.Ui;
import Utility.Storage;
import Utility.Parser;

import Command.Command;

/**
 * Represents a chatbot of the name Duke.
 */
public class Duke {
    private TaskList list;
    private Ui ui;
    private Storage storage;

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
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

