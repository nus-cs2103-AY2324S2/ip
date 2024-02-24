package ficin;

import ficin.task.Task;
import ficin.task.TaskList;

/**
 * The AddCommand class represents a command to add a new task to the task list.
 * It is a subclass of the Command class.
 */
public class AddCommand extends Command {

    /**
     * Executes the add command to add a new task to the task list based on user input.
     *
     * @param taskList  The task list to which the new task will be added.
     * @param ui        The user interface for displaying messages.
     * @param userInput The user input specifying the new task to be added.
     * @throws DukeException If the user input is empty or if the input format is unrecognized.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        if (userInput.trim().isEmpty()) { // Checks for empty or whitespace-only strings
            throw new DukeException("The description of a task cannot be empty.");
        }

        try {
            Task newTask = Parser.parseTask(userInput);
            if (newTask != null) {
                taskList.addTask(newTask);
                ui.showTaskAdded(newTask, taskList.getSize());
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            throw new DukeException("Error processing task input: " + e.getMessage());
        }
    }
}
