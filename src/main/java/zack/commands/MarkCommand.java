package zack.commands;

import java.io.IOException;

import zack.ZackException;
import zack.tasks.Task;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

/**
 * Command class responsible for marking a task as done or undone.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    /**
     * Constructs a MarkCommand with the specified task index and the desired status (done or undone).
     *
     * @param index  The index of the task to be marked.
     * @param isDone True if the task should be marked as done, false if it should be marked as undone.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the MarkCommand, marking the task at the specified index as done or undone.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui to display user messages.
     * @param storage The Storage to save the updated task list.
     * @throws ZackException If there is an error marking the task.
     * @throws IOException   If there is an error in saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new ZackException("Task index is out of range. Please enter a number between 1 and "
                    + tasks.getSize() + ".");
        }
        Task task = tasks.getTask(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.unmark();
        }

        storage.save(tasks.getAllTasks());
        return ui.showMarkedTask(task, isDone);
    }
}
