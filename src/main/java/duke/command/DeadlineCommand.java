package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private Deadline task;

    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        ui.showAddTask(taskList, task);
    }
}
