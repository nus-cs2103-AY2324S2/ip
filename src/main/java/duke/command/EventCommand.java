package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {
    private Event task;

    public EventCommand(Event task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        ui.showAddTask(taskList, task);
    }
}
