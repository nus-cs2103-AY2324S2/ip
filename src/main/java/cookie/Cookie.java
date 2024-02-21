package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.command.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the main Duke application class responsible for running the program.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    protected boolean isExit = false;

    /**
     * Constructs a Duke object with the specified file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./tasks.txt");
        tasks = new TaskList();
        storage.getFileContents(tasks);
    }

    /**
     * Processes the user input and generates a response.
     *
     * This method parses the user input, executes the corresponding command,
     * and generates a response message based on the command execution.
     * If the command indicates that the program should exit, it sets the {@code isExit}
     * flag accordingly.
     *
     * @param input The user input to be processed.
     * @return The response message generated based on the user input and command execution.
     * @throws DukeException If an error occurs during command parsing or execution.
     */
    public String getResponse(String input) throws DukeException {
        try {
            Command command = Parser.parseCommand(input);
            isExit = command.isBye();
            return command.executeAndReply(ui, tasks, storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        System.out.println(ui.showWelcomeMessage());
    }

    /**
     * The entry point for running the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}

