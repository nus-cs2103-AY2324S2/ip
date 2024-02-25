package duke;

import java.io.IOException;

import duke.exceptions.DukeException;

/**
 * Main program of Duke that runs the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private LoanRecords loans;

    /**
     * Constructs a <code>Duke</code> to start the program.
     *
     * @param taskDataPath Tasks data path for persistent task storage.
     * @param loanRecordsPath Loan records path for persistent task storage.
     */
    public Duke(String taskDataPath, String loanRecordsPath) {
        storage = new Storage(taskDataPath, loanRecordsPath);
        try {
            tasks = new TaskList(storage.loadTasks());
            loans = new LoanRecords(storage.loadLoans());
        } catch (IOException ie) {
            tasks = new TaskList();
            loans = new LoanRecords();
        }
        assert tasks != null : "TaskList should not be null!";
        assert loans != null : "Loan Records should not be null!";
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
        try {
            Command c = Parser.parse(command);
            return c.execute(tasks, loans, storage);
        } catch (DukeException de) {
            return de.getMessage();
        }
    }
}
