package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.storage.Storage;

public class ToDoCommand extends Command {

    private ToDo toDo;

    public ToDoCommand(String description) {
        this.toDo = new ToDo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(toDo);
        ui.showAddMsg(toDo, tasks.getTaskSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
