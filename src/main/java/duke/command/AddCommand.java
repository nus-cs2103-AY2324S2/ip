package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDateTime;

/**
 * An {@code AddCommand} is a {@code Command} that adds a {@code Task} to a {@code TaskList}.
 */
public class AddCommand extends Command {
    /**
     * The task to add.
     */
    private final Task task;

    /**
     * Creates a new {@code AddCommand} for a {@code Todo} task with the given description.
     *
     * @param desc The description of the {@code Todo} task.
     */
    public AddCommand(String desc) {
        task = new Todo(desc);
    }

    /**
     * Creates a new {@code AddCommand} for a {@code Deadline} task with the given description and deadline.
     *
     * @param desc The description of the {@code Deadline} task.
     * @param deadline The deadline of the {@code Deadline} task.
     */
    public AddCommand(String desc, LocalDateTime deadline) {
        task = new Deadline(desc, deadline);
    }

    /**
     * Creates a new {@code AddCommand} for an {@code Event} task with the given description, start time, and end time.
     *
     * @param desc The description of the {@code Event} task.
     * @param from The start time of the {@code Event} task.
     * @param to The end time of the {@code Event} task.
     */
    public AddCommand(String desc, LocalDateTime from, LocalDateTime to) {
        task = new Event(desc, from, to);
    }

    /**
     * {@inheritDoc}
     *
     * Adds the task to the task list and saves the updated task list.
     * Displays the task that was added to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks);
        return ui.showAdded(task) + ui.showTasksStatus(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
