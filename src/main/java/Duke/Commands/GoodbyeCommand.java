package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import java.io.IOException;

public class GoodbyeCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
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
