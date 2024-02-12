package duke.command;

import duke.JamieException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;
/**
 * Represents a command to mark a task as complete.
 */
public class CompleteTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a CompleteTaskCommand with the specified task index.
     *
     * @param taskIndex The index of the task to mark as complete.
     */
    public CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking the specified task as complete in the task list,
     * saving it to storage, and displaying a message indicating the task has been marked as done.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return
     * @throws IOException If there is an error while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JamieException {
        tasks.completeTask(this.taskIndex - 1);
        storage.save(tasks);
        ui.showMarkAsDoneMessage(tasks.getTask(this.taskIndex - 1));
    }
}
