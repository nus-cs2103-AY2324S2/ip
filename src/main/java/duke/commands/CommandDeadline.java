package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

/**
 * Represents the command for adding a deadline task to the task list in the Duke application.
 */
public class CommandDeadline extends Command {
    private final Deadline deadline;

    /**
     * Constructs a new CommandDeadline object with the specified deadline task.
     *
     * @param deadline The deadline task to be added to the task list.
     */
    public CommandDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.add(this.deadline);

        storage.saveTasks(tasks);

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.deadline, tasks.size());
    }
}
