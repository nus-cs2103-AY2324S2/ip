package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing the action of marking a task as undone.
 *
 * <p>The {@code UnmarkCommand} class encapsulates the information and actions
 * required to mark a task in the task list as done. It inherits from the {@code Command}
 * class and implements the behavior specific to marking a task as done.</p>
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
                        ui.appendResponse(String.format("Oh no.. Marked task %d as undone: %s\n",
                            index + 1, task.getDescription()));
                    } else {
                        throw new DukeException(String.format("I can't do that.. Task %d is already undone! ~(T_T)\n",
                            index + 1));
                    }
                } else {
                    throw new DukeException(String.format("I can't do that.. Task index %s is out of range! ~(T_T)\n",
                        arguments.get(0)));
                }
            } catch (DukeException e) {
                ui.appendResponse(e.getMessage());
                return tasks;
            } catch (NumberFormatException e) {
                ui.appendResponse("Sigh.. That's not a valid number! Try 'unmark <NUMBER>'.\n");
                return tasks;
            }
        } else {
            ui.appendResponse("I can't do that.. You need to specify a task index! Try \"mark <index>\".. \n");
        }

        return tasks;
    }
}
