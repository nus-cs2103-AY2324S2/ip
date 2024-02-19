package duke.command;

import java.util.List;

import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * An instruction class that encapsulates the action of adding a task
 * into a TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Represents a command to add a task to the task list.
     *
     * <p>The {@code AddCommand} class encapsulates the information and actions
     * required to add a task to the task list. It inherits from the {@code Command}
     * class and implements the behavior specific to adding a task.</p>
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        super("add", List.of());
        this.task = task;
    }

    /**
     * Executes the add task command.
     *
     * <p>This method adds the specified task to the task list and prints a confirmation message.
     * It then returns the updated task list.</p>
     *
     * @param tasks The task list to which the task is added.
     * @return The updated task list after adding the task.
     */
    public TaskList execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
        Integer count = tasks.getNoOfTasks();

        String message = String.format(
            "\n        ~~~ >^o_o^< ~~~\nGot it! I've added this task:\n"
            + "- %s\n\nYou have %d task(s) in the list.\n",
            task, count);

        ui.appendResponse(message);
        return tasks;
    }
}
