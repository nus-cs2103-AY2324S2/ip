package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class PrintListCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    public PrintListCommand(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    @Override
    public void execute() throws DukeException {
        this.ui.printList(this.taskList);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
