package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Finds a task from the task list.
 */
public class FindCommand extends Command {
    private String taskName;

    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        TaskList matchingTasks = taskList.findTasks(taskList, taskName);
        ui.showFindTask(matchingTasks);
    }
}
