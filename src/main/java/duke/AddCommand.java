package duke;

import java.io.IOException;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand implements Command {
    private String userInput;

    /**
     * Constructs an AddCommand with the given user input.
     *
     * @param userInput The user input specifying the task to be added.
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the add command, creating a new task, adding it to the task list,
     * displaying a confirmation message, and saving the updated task list to the file.
     *
     * @param tasks   The task list to which the new task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list to the file.
     * @return A string representation of the command result.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask = TaskFactory.createTask(userInput);

            assert newTask != null : "Failed to create new task";

            // Check if the task description is null
            if (newTask.getDescription().isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            }

            tasks.addTask(newTask);
            ui.showConfirmationMessage(tasks.getTasks());

            // Prepare the result message
            String resultMessage = "Task added:\n" + newTask + "\n";
            resultMessage += "Now you have " + tasks.getTasks().size() + " tasks in the list.";

            storage.saveTasksToFile(tasks);

            return resultMessage;
        } catch (AssertionError e) {
            throw new DukeException(e.getMessage());
        } catch (IOException e) {
            ui.showIoExceptionMessage();
            return "Error saving or loading tasks. Please check the file.";
        }
    }
}
