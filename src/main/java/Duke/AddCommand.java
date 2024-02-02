package Duke;

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
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask = TaskFactory.createTask(userInput);
            tasks.addTask(newTask);
            ui.showConfirmationMessage(tasks.getTasks());
            storage.saveTasksToFile(tasks);
        } catch (IOException e) {
            // Handle or log the IOException as needed
            ui.showIoExceptionMessage();
        }
    }
}
