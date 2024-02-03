package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandUnmark extends Command {
    private Integer taskIndex;

    public CommandUnmark(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskIndex);
        task.unmark();

        storage.saveTasks(tasks);

        ui.showMessage(String.format("OK, I've marked this task as not done yet:\n  %s", task));
    }
}
