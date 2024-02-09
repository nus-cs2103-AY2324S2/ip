package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

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
    public String execute() throws DukeException {
        try {
            this.storage.save(this.taskList);
        } catch (IOException error) {
            return this.ui.printErrorMessage(error.getMessage());
        }
        return this.ui.sayGoodbye();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
