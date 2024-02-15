package duck;

import java.io.IOException;

/**
 * Represents a command to untag a task.
 */
public class UntagCommand implements Command {
    private int taskIndex;

    /**
     * Constructs an UntagCommand object with the specified task index.
     *
     * @param taskIndex The index of the task to be untagged.
     */
    public UntagCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the untag command, removing the tag from the specified task.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for interaction.
     * @param storage The storage for saving tasks to a file.
     * @return A string message indicating the result of the untag operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Assert that taskIndex is within valid range
        assert taskIndex >= 0 && taskIndex < tasks.getTasks().size() : "Invalid task index";

        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task taggingTask = tasks.getTask(taskIndex);
                taggingTask.clearTag();
                storage.saveTasksToFile(tasks);
                String resultMessage = String.format("Task %d has been untagged.", taskIndex + 1);
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

