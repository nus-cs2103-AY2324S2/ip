package arc.commands;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.Deadline;
import arc.tasks.TaskList;

/**
 * Represents the command for adding a deadline task to the task list in the Arc application.
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
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        tasks.add(this.deadline);

        storage.saveTasks(tasks);

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.deadline, tasks.size());
    }
}
