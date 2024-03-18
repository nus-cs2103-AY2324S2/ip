package coat.command;

import java.util.List;

import coat.exception.CoatException;
import coat.task.Task;
import coat.task.TaskList;
import coat.ui.Messages;
import coat.ui.Ui;

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
    public TaskList execute(TaskList tasks, Ui ui) throws CoatException {
        if (arguments.isEmpty()) {
            ui.appendResponse(Messages.UNMARK_MISSING_INDEX_ERROR.getMessage());
            return tasks;
        }

        try {
            int index = Integer.parseInt(arguments.get(0)) - 1;
            if (index < 0 || index >= tasks.getNoOfTasks()) {
                throw new CoatException(String.format(Messages.UNMARK_INDEX_OUT_OF_RANGE_ERROR.getMessage(),
                        arguments.get(0)));
            }

            Task task = tasks.getTask(index);
            if (task.isDone()) {
                task.markUndone();
                ui.appendResponse(String.format(Messages.UNMARK_SUCCESS.getMessage(), index + 1,
                    task.getDescription()));
            } else {
                throw new CoatException(String.format(Messages.UNMARK_ALREADY_UNDONE_ERROR.getMessage(), index + 1));
            }
        } catch (NumberFormatException e) {
            ui.appendResponse(Messages.UNMARK_INVALID_NUMBER_ERROR.getMessage());
        } catch (CoatException e) {
            ui.appendResponse(e.getMessage());
        }

        return tasks;
    }
}
