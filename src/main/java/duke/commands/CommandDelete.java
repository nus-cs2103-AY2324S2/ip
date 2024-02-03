package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

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
