package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;


/**
 * Represents the Chatbot object.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);

        try {
            this.tasks = new TaskList(this.storage.readTasksFile()
                    .toArray(new Task[0])
            );
        } catch (DukeException e) {
            this.ui.showError(e);

        }
    }

    private void run() {
        System.out.println(this.ui.showGreet());

        while (!isExit) {
            String input = this.ui.readInput();
            System.out.println(this.getResponse(input));
        }
    }

    /**
     * Returns the response to the user's input.
     *
     * @param input The user's input.
     * @return The response to the user's input
     */
    protected String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            String response = cmd.generateReply(this.tasks, this.ui, this.storage);
            this.isExit = cmd.isExit();
            return response;
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
