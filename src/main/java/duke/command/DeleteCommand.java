package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

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
    public TaskList execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            if (arguments.isEmpty()) {
                throw new DukeException(Messages.DELETE_ERROR_MESSAGE);
            }

            int index = Integer.parseInt(arguments.get(0)) - 1;

            if (index >= 0 && index < tasks.getNoOfTasks()) {
                tasks.deleteTask(index);
                ui.appendResponse(String.format(Messages.SUCCESS_DELETE_MESSAGE, index + 1));
            } else {
                throw new DukeException(String.format(Messages.INDEX_ERROR_MESSAGE,
                    arguments.get(0)));
            }
        } catch (DukeException e) {
            String message = String.format("%s", e.getMessage());
            ui.appendResponse(message);
            return tasks;
        } catch (NumberFormatException e) {
            ui.appendResponse(Messages.DELETE_INVALID_INDEX_ERROR_MESSAGE);
            return tasks;
        }

        return tasks;
    }
}
