package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

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
