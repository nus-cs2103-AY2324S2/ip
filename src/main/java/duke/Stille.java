package duke;

import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.UserInterface;

/**
 * Main driver class, initializes the required classes and starts the operation.
 */
public class Stille {
    private final UserInterface ui;
    private final Storage storage;
    private final TaskList list;

    public Stille() {
        this.ui = new UserInterface();
        this.storage = new Storage();
        this.list = new TaskList();

        try {
            list.loadFromSaveFormat(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    /**
     * Main logic of application, displays opening message and enters command loop.
     */
    public void run() {
        ui.showOpeningMessage();

        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parseInput(input);
                isExit = c.execute(this.list, this.ui);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        this.exit();
    }

    /**
     * At the end of operation, save the current tasklist, output closing message.
     */
    public void exit() {
        try {
            this.storage.save(this.list.toSaveFormat());
        } catch (DukeException e) {
            ui.showError(e);
        } finally {
            ui.showClosingMessage();
        }
    }

    public static void main(String[] args) {
        new Stille().run();
    }
}
