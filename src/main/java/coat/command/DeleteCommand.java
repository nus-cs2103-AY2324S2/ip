package coat.command;

import java.util.List;

import coat.exception.CoatException;
import coat.task.TaskList;
import coat.ui.Messages;
import coat.ui.Ui;

/**
 * A command class representing the action of deleting a task from the task list.
 *
 * <p>The {@code DeleteCommand} class encapsulates the information and actions
 * required to delete a task from the task list. It inherits from the {@code Command}
 * class and implements the behavior specific to deleting a task.</p>
 */
public class DeleteCommand extends Command {
    public DeleteCommand(List<String> arguments) {
        super("delete", arguments);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) throws CoatException {
        try {
            if (arguments.isEmpty()) {
                throw new CoatException(Messages.DELETE_ERROR.getMessage());
            }

            int index = Integer.parseInt(arguments.get(0)) - 1;

            if (index >= 0 && index < tasks.getNoOfTasks()) {
                tasks.deleteTask(index);
                ui.appendResponse(String.format(Messages.DELETE_SUCCESS.getMessage(), index + 1));
            } else {
                throw new CoatException(String.format(Messages.INDEX_ERROR.getMessage(),
                    arguments.get(0)));
            }
        } catch (CoatException e) {
            String message = String.format("%s", e.getMessage());
            ui.appendResponse(message);
            return tasks;
        } catch (NumberFormatException e) {
            ui.appendResponse(Messages.DELETE_INVALID_INDEX_ERROR.getMessage());
            return tasks;
        }

        return tasks;
    }
}
