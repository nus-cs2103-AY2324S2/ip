package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Adds a event task to the task list.
 */
public class EventCommand extends Command {
    private Event task;

    public EventCommand(Event task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        return ui.showAddTask(taskList, task);
    }
}
