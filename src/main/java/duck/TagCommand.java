package duck;

import java.io.IOException;

/**
 * Represents a command to tag a task.
 */
public class TagCommand implements Command {
    private int taskIndex;
    private String tag;
    /**
     * Constructs a TagCommand object with the specified task index and tag.
     *
     * @param taskIndex The index of the task to be tagged.
     * @param tag The tag to be applied to the task.
     */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Executes the tag command, tagging the specified task with the given tag.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for interaction.
     * @param storage The storage for saving tasks to a file.
     * @return A string message indicating the result of the tag operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Assert that taskIndex is within valid range
        assert taskIndex >= 0 && taskIndex < tasks.getTasks().size() : "Invalid task index";

        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task taggingTask = tasks.getTask(taskIndex);
                taggingTask.setTag(tag);
                storage.saveTasksToFile(tasks);
                String resultMessage = String.format("Task %d has been tagged as '%s'.", taskIndex + 1, tag);
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
