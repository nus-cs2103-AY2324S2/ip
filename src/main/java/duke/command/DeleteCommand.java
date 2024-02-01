package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IllegalParamException;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int id;
    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IllegalParamException {
        list.deleteTask(this.id);
        storage.save(list);
        ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");

    }
}