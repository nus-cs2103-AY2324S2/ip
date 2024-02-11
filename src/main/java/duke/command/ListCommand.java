package duke.command;

import java.util.List;

import duke.task.TaskList;

/**
 * A command class representing the action of listing all tasks.
 *
 * <p>The {@code ListCommand} class encapsulates the information and actions
 * required to list all tasks stored in the task list. It inherits from the {@code Command}
 * class and implements the behavior specific to listing tasks.</p>
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("list", List.of());
    }

    @Override
    public TaskList execute(TaskList tasks) {
        System.out.printf("\nHere's your list! ~~(^-^)\n");
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.getTask(i));
        }
        return tasks;
    }
}
