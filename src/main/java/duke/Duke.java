package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.exception.DukeException;

/**
 * Represents the Chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    // deals with interactions with the user
    private Ui ui;

    /**
     * Constructor for the Chatbot and retrieve the past log from the storage.
     *
     * @param filePath String of the path of the file to retrieve the past log taskList from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Chatbot and takes in input while it is active. Terminates when input
     * command is "bye"
     */
    public void run() {
        boolean isActive = true;
        ui.greet();

        while (isActive) {
            try {
                String userInput = this.ui.getInput();
                Command command = Parser.parseCommand(userInput);
                command.execute(this.storage, this.tasks, this.ui);
                isActive = command.getActive();
            } catch (DukeException e) {
                Ui.printError(e);
            }
        }

    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}

