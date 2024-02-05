package duke;

import java.io.IOException;

import duke.exceptions.DukeException;
import javafx.application.Application;

/**
 * Main program of Duke that runs the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a <code>Duke</code> to start the program.
     *
     * @param filePath File path for persistent task storage.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs an instance of <code>Duke</code>.
     */
    public String greet() {
        return this.ui.greet();
    }

    public String readCommand(String command) {
        String reply = "";
        try {
            Command c = Parser.parse(command);
            reply = c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException de) {
            System.out.println(de);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reply;
    }
}
