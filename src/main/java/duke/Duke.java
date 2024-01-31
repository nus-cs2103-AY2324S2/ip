package duke;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.Parser;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.LoadStorageException;
import duke.command.Command;

/**
 * The main class for the chatbot application.
 * */
public class Duke {
    Storage storage;
    TaskList taskList;
    Ui ui;

    public Duke(String relativeFilePath) {
        this.ui = new Ui();

        this.storage = new Storage(relativeFilePath);

        try {
            this.taskList = new TaskList(storage.load());
        } catch (LoadStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Driver method for the chatbot.
     * */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/db.txt").run();
    }
}
