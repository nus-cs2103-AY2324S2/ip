package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

/**
 * A command class representing the action of marking a task as undone.
 *
 * <p>The {@code UnmarkCommand} class encapsulates the information and actions
 * required to mark a task in the task list as undone. It inherits from the {@code Command}
 * class and implements the behavior specific to marking a task as undone.</p>
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(List<String> arguments) {
        super("unmark", arguments);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) throws DukeException {
        // Assuming arguments is a list of one string
        if (!arguments.isEmpty()) {
            try {
                int index = Integer.parseInt(arguments.get(0)) - 1;
                if (index >= 0 && index < tasks.getNoOfTasks()) {
                    Task task = tasks.getTask(index);
                    if (task.isDone()) {
                        task.markUndone();
                        ui.appendResponse(String.format(Messages.UNMARK_SUCCESS.getMessage(),
                            index + 1, task.getDescription()));
                    } else {
                        throw new DukeException(String.format(Messages.UNMARK_ALREADY_UNDONE_ERROR.getMessage(),
                            index + 1));
                    }
                } else {
                    throw new DukeException(String.format(Messages.UNMARK_INDEX_OUT_OF_RANGE_ERROR.getMessage(),
                        arguments.get(0)));
                }
            } catch (DukeException e) {
                ui.appendResponse(e.getMessage());
                return tasks;
            } catch (NumberFormatException e) {
                ui.appendResponse(Messages.UNMARK_INVALID_NUMBER_ERROR.getMessage());
                return tasks;
            }
        } else {
            ui.appendResponse(Messages.UNMARK_MISSING_INDEX_ERROR.getMessage());
        }

        return tasks;
    }
}
