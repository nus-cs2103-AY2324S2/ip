package arc.commands;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.Task;
import arc.tasks.TaskList;

/**
 * Represents the command for marking a task as done in the Arc application.
 */
public class CommandMark extends Command {
    private final Integer taskIndex;

    /**
     * Constructs a new CommandMark object with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public CommandMark(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        Task task = tasks.get(this.taskIndex);
        task.mark();

        storage.saveTasks(tasks);

        return String.format("Nice! I've marked this task as done:\n  %s", task);
    }
}
