package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandDelete extends Command {
    private Integer taskIndex;

    public CommandDelete(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.remove(this.taskIndex);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task, tasks.size()));
    }
}
