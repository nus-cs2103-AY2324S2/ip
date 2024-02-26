package commands;

import exception.SnoozeException;
import objects.Task;
import objects.TaskList;
import objects.ToDo;
import view.SnoozedTask;

/**
 * The Snooze class represents a command to snooze a task.
 */
public class Snooze implements Command {
    private final TaskList tasks;
    private final int index;

    /**
     * Constructs a Snooze command with the specified task list and task index.
     *
     * @param tasks The task list.
     * @param index The index of the task to snooze.
     */
    public Snooze(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Executes the Snooze command.
     *
     * @return A message indicating the snoozed task.
     * @throws SnoozeException If the task is a ToDo task, which cannot be snoozed.
     */
    @Override
    public String execute() throws SnoozeException {
        Task task = tasks.get(index);

        if (task instanceof ToDo) {
            throw new SnoozeException();
        } else {
            task.snooze();
        }

        return SnoozedTask.display(task);
    }
}
