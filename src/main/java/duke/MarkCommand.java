package duke;

import java.io.IOException;

/**
 * The MarkCommand class represents a command to mark a task as done.
 * It implements the Command interface.
 */
public class MarkCommand implements Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the given task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, marking the specified task as done.
     * Updates the UI and saves the tasks to the storage.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for displaying user interface messages.
     * @param storage The Storage object for saving tasks to the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Assert that task index is within valid range
        assert taskIndex >= 0 && taskIndex < tasks.getTasks().size() : "Invalid task index";

        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task markedTask = tasks.getTask(taskIndex);
                tasks.markTaskAsDone(taskIndex);
                storage.saveTasksToFile(tasks);
                ui.showMarkTaskAsDone(markedTask);
                return "Task marked as done:\n" + markedTask + "\n";
            } else {
                ui.invalidTaskIndex();
                String string = " Invalid task index. Please enter a valid task index.";
                return string;
            }
        } catch (IOException e) {
            ui.showIoExceptionMessage();
            return "Error saving or loading tasks. Please check the file.";
        }
    }
}

