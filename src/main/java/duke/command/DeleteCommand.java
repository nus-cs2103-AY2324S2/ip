package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.TaskList;

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
    public TaskList execute(TaskList tasks) throws DukeException {
        try {
            if (arguments.isEmpty()) {
                throw new DukeException("Hey, you need to tell me which one to delete! Try 'delete <INDEX>'. ~(>_<)\n");
            }

            int index = Integer.parseInt(arguments.get(0)) - 1;

            if (index >= 0 && index < tasks.getNoOfTasks()) {
                tasks.deleteTask(index);
                System.out.printf("\nDeleted task %d ~(^-^)\n ", index + 1);
            } else {
                throw new DukeException(String.format("I can't do that.. Task index %s is out of range! ~(T_T)\n",
                    arguments.get(0)));
            }
        } catch (DukeException e) {
            System.out.printf("%s", e.getMessage());
            return tasks;
        } catch (NumberFormatException e) {
            System.out.printf("Sigh.. That's not a valid number! Try 'delete <NUMBER>'.\n");
            return tasks;
        }

        return tasks;
    }
}
