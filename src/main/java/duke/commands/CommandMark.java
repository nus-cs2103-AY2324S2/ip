package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents the command for marking a task as done in the Duke application.
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
     * Executes the "mark" command, which marks the specified task as done in the task list.
     *
     * @param tasks   The task list containing the tasks to be marked.
     * @param storage The storage component for saving the updated task list.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskIndex);
        task.mark();

        storage.saveTasks(tasks);

        return String.format("Nice! I've marked this task as done:\n  %s", task);
    }
}
