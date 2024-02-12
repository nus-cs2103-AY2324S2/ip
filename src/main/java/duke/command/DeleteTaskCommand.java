package duke.command;

import duke.JamieException;
import duke.TaskList;
import duke.task.Task;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private final int taskNum;

    /**
     * Constructs a DeleteTaskCommand with the specified task number.
     *
     * @param taskNum The number of the task to delete.
     */
    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by deleting the specified task from the task list.
     * It also saves the updated task list to storage, and displaying a message
     * indicating the task has been deleted.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return
     * @throws IOException If there is an error while saving the task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JamieException {
        assert this.taskNum > 0 : "Input task number cannot be less than 1";
        assert this.taskNum <= tasks.getTasksSize()
                : "Input task number cannot be more than total number of tasks.";
        Task toDelete = tasks.getTask(this.taskNum - 1);
        tasks.deleteTask(this.taskNum - 1);
        storage.save(tasks);
        ui.showDeleteMessage(toDelete, tasks);
    }
}

