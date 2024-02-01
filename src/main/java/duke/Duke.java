package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the Duke Chatbot object.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for Duke.
     *
     * @param filePath Filepath to store tasks data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.readTasksFile());
        } catch (DukeException e) {
            this.ui.error(e);

        }
    }


    private void run() {
        this.ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = this.ui.readInput();
                Command cmd = Parser.parse(input);
                cmd.execute(this.tasks, this.ui, this.storage);
                isExit = cmd.isExit();

            } catch (DukeException e) {
                ui.error(e);
            }
        }
    }


    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
