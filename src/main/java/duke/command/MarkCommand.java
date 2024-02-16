package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

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
    public TaskList execute(TaskList tasks) throws DukeException {
        if (arguments.isEmpty()) {
            System.out.printf("I can't do that.. You need to specify a task index! Try \"mark <index>\".. \n");
            return tasks;
        }

        try {
            int index = Integer.parseInt(arguments.get(0)) - 1;
            if (index < 0 || index >= tasks.getNoOfTasks()) {
                throw new DukeException(String.format("I can't do that.."
                    + " Task index %s is out of range! ~(T_T)\n", arguments.get(0)));
            }

            Task task = tasks.getTask(index);
            if (task.isDone()) {
                throw new DukeException(String.format("I can't do that.."
                    + " Task %d is already done! ~(T_T)\n", index + 1));
            }

            task.markAsDone();
            System.out.printf("Marked task %d as done: %s\nGood job! ~(^-^)\n", index + 1, task.getDescription());
        } catch (NumberFormatException e) {
            System.out.printf("Sigh.. That's not a valid number! Try 'mark <NUMBER>'.\n");
        }

        return tasks;
    }

}
