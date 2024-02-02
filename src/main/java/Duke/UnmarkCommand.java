package Duke;

import java.io.IOException;

/**
 * The UnmarkCommand class represents a command to mark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to mark as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the UnmarkCommand, marking the specified task as not done in the task list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list to a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task markedTask = tasks.getTask(taskIndex);
                tasks.markTaskAsNotDone(taskIndex);
                storage.saveTasksToFile(tasks);
                ui.showUnmarkTaskAsDone(markedTask);
            } else {
                ui.invalidTaskIndex();
            }
        } catch (IOException e) {
            ui.showIoExceptionMessage();
        }
    }
}
