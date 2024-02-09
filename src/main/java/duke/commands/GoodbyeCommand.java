package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Represents a command to exit the chatBot.
 */
public class GoodbyeCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;

    /**
     * Constructor for the GoodbyeCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     */
    public GoodbyeCommand(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    @Override
    public void execute() throws DukeException {
        try {
            this.storage.save(this.taskList);
        } catch (IOException error) {
            this.ui.printErrorMessage(error.getMessage());
        }
        this.ui.sayGoodbye();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
