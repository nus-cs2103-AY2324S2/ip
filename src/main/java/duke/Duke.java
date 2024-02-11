package duke;

import java.io.IOException;

import duke.exceptions.DukeException;

/**
 * Main program of Duke that runs the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a <code>Duke</code> to start the program.
     *
     * @param filePath File path for persistent task storage.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            this.tasks = new TaskList();
        }
        assert tasks != null : "TaskList should not be null!";
    }

    /**
     * Runs an instance of <code>Duke</code>.
     */
    public String greet() {
        return "Hello! I'm Hatsune Miku!\n"
                + " What can I do for you?";
    }

    /**
     * Returns a goodbye message.
     *
     * @return Goodbye message string.
     */
    public String goodbye() {
        return "Bye, Hope to see you again soon!";
    }

    /**
     * Reads command String off of user input in <code>MainWindow</code>.
     *
     * @param command command String to be parsed.
     * @return Dialogue for Duke after command processing.
     */
    public String readCommand(String command) {
        String reply = "";
        try {
            Command c = Parser.parse(command);
            reply = c.execute(this.tasks, this.storage);
        } catch (DukeException de) {
            return de.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
        return reply;
    }
}
