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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
        ui.showWelcomeMessage();
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

