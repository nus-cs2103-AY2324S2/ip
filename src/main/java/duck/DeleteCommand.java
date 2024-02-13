package duck;

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
     * @return A string representation of the command result.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Assert that taskIndex is within valid range
        assert taskIndex >= 0 && taskIndex < tasks.getTasks().size() : "Invalid task index";

        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task taskBeforeDeletion = tasks.getTask(taskIndex);
                tasks.deleteTask(taskIndex);
                storage.saveTasksToFile(tasks);
                ui.showDeleteMessage(tasks.getTasks(), taskBeforeDeletion);
                // Prepare the result message for successful deletion
                String resultMessage = "Task deleted:\n" + taskBeforeDeletion + "\n";
                resultMessage += "Now you have " + tasks.getTasks().size() + " tasks in the list.";
                return resultMessage;
            } else {
                ui.invalidTaskIndex();
                return " Invalid task index. Please enter a valid task index.";
            }
        } catch (IOException e) {
            ui.showIoExceptionMessage();
            return "Error saving or loading tasks. Please check the file.";
        }
    }
}

