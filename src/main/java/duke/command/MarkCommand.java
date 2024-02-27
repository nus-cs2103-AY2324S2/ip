package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

/**
 * A command class representing the action of marking a task as done.
 *
 * <p>The {@code MarkCommand} class encapsulates the information and actions
 * required to mark a task in the task list as done. It inherits from the {@code Command}
 * class and implements the behavior specific to marking a task as done.</p>
 */
public class MarkCommand extends Command {

    public MarkCommand(List<String> arguments) {
        super("mark", arguments);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) throws DukeException {
        if (arguments.isEmpty()) {
            ui.appendResponse(String.format(Messages.MARK_NO_INDEX_ERROR.getMessage()));
            return tasks;
        }

        try {
            int index = Integer.parseInt(arguments.get(0)) - 1;
            if (index < 0 || index >= tasks.getNoOfTasks()) {
                throw new DukeException(String.format(Messages.MARK_INDEX_OOR_ERROR.getMessage(), arguments.get(0)));
            }

            Task task = tasks.getTask(index);
            if (task.isDone()) {
                throw new DukeException(String.format(Messages.MARK_TASKMARKED_ERROR.getMessage(), index + 1));
            }

            task.markAsDone();
            ui.appendResponse(String.format(Messages.MARK_SUCCESS.getMessage(), index + 1, task.getDescription()));
        } catch (NumberFormatException e) {
            ui.appendResponse(String.format(Messages.MARK_INVALID_INDEX_ERROR.getMessage()));
        } catch (DukeException e) {
            ui.appendResponse(String.format("%s", e.getMessage()));
        }

        return tasks;
    }

}
