package duck;

import java.io.IOException;

public class UntagCommand implements Command {
    private int taskIndex;
    public UntagCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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

