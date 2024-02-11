package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;


public class UnmarkCommand extends Command {
    public UnmarkCommand(List<String> arguments) {
        super("unmark", arguments);
    }

    @Override
    public TaskList execute(TaskList tasks) throws DukeException {
        // Assuming arguments is a list of one string
        if (!arguments.isEmpty()) {
            try {
                int index = Integer.parseInt(arguments.get(0)) - 1;
                if (index >= 0 && index < tasks.getNoOfTasks()) {
                    Task task = tasks.getTask(index);
                    if (task.isDone()) {
                        task.markUndone();
                        System.out.printf("Oh no.. Marked task %d as undone: %s\n", index + 1, task.getDescription());
                    } else {
                        throw new DukeException(String.format("I can't do that.. Task %d is already undone! ~(T_T)\n",
                            index + 1));
                    }
                } else {
                    throw new DukeException(String.format("I can't do that.. Task index %s is out of range! ~(T_T)\n",
                        arguments.get(0)));
                }
            } catch (DukeException e) {
                System.out.printf("%s", e.getMessage());
                return tasks;
            } catch (NumberFormatException e) {
                System.out.printf("Sigh.. That's not a valid number! Try 'unmark <NUMBER>'.\n");
                return tasks;
            }
        } else {
            System.out.printf("I can't do that.. You need to specify a task index! Try \"mark <index>\".. \n");
        }

        return tasks;
    }
}
