package Duke;

import java.io.IOException;

/**
 * The DeleteCommand class represents a command to delete a task from the TaskList.
 * It implements the Command interface.
 */
public class DeleteCommand implements Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command, removing the specified task from the TaskList.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui object for displaying user interface messages.
     * @param storage The Storage object for saving and loading tasks to and from a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task taskBeforeDeletion = tasks.getTask(taskIndex);
                tasks.deleteTask(taskIndex);
                storage.saveTasksToFile(tasks);
                ui.showDeleteMessage(tasks.getTasks(), taskBeforeDeletion);
            } else {
                ui.invalidTaskIndex();
            }
        } catch (IOException e) {
            ui.showIoExceptionMessage();
        }
    }
}

