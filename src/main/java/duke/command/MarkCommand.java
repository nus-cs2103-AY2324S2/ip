package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing the action of marking a task as done.
 *
 * <p>The {@code MarkCommand} class encapsulates the information and actions
 * required to mark a task in the task list as done. It inherits from the {@code Command}
 * class and implements the behavior specific to marking a task as done.</p>
 */
public class MarkCommand extends Command {
    private static final String INDEX_UNSPECIFIED_ERROR_MESSAGE = "I can't do that.."
        + " You need to specify a task index! Try \"mark <index>\".. \n";
    private static final String INDEX_OUT_OF_RANGE_ERROR_MESSAGE = "I can't do that.."
                    + " Task index %s is out of range! ~(T_T)\n";
    private static final String INDEX_ALREADY_MARKED_ERROR_MESSAGE = "I can't do that.."
                    + " Task %d is already done! ~(T_T)\n";
    private static final String SUCCESS_MARKED_MESSAGE = "Marked task %d as done: %s\nGood job! ~(^-^)\n";
    private static final String INVALID_NUMBER_ERROR_MESSAGE = "Sigh.. That's not a valid number!"
        + " Try 'mark <NUMBER>'.\n";

    public MarkCommand(List<String> arguments) {
        super("mark", arguments);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) throws DukeException {
        if (arguments.isEmpty()) {
            ui.appendResponse(String.format(INDEX_UNSPECIFIED_ERROR_MESSAGE));
            return tasks;
        }

        try {
            int index = Integer.parseInt(arguments.get(0)) - 1;
            if (index < 0 || index >= tasks.getNoOfTasks()) {
                throw new DukeException(String.format(INDEX_OUT_OF_RANGE_ERROR_MESSAGE, arguments.get(0)));
            }

            Task task = tasks.getTask(index);
            if (task.isDone()) {
                throw new DukeException(String.format(INDEX_ALREADY_MARKED_ERROR_MESSAGE, index + 1));
            }

            task.markAsDone();
            ui.appendResponse(String.format(SUCCESS_MARKED_MESSAGE, index + 1, task.getDescription()));
        } catch (NumberFormatException e) {
            ui.appendResponse(String.format(INVALID_NUMBER_ERROR_MESSAGE));
        } catch (DukeException e) {
            ui.appendResponse(String.format("%s", e.getMessage()));
        }

        return tasks;
    }

}
