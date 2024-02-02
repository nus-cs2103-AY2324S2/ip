package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

/**
 * Adds a to-do task to the task list.
 */
public class ToDoCommand extends Command {
    private ToDo task;

    public ToDoCommand(ToDo task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        ui.showAddTask(taskList, task);
    }
}
