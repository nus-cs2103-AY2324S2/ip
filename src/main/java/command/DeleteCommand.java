package command;

import exceptions.DukeException;
import task.Task;
import task.TaskList;
import utilities.Storage;

/**
 * Controls what happens when a task is deleted.
 */
public class DeleteCommand extends Command {
    /**
     * The task index of the task that is to be deleted.
     */
    private int taskToDeleteIndex;

    /**
     * DeleteCommand class constructor.
     * @param userInput The input that the user types into the command line.
     */
    public DeleteCommand(String userInput) {
        super(false);
        this.taskToDeleteIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    /**
     * Executes the delete task process.
     * @param taskList The task list that the task is deleted from.
     * @param storage The storage that the task list is stored in after the task is deleted.
     * @return The response expected from the chatbot.
     * @throws DukeException The exception thrown when the user input is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            Task removedTask = taskList.deleteTask(this.taskToDeleteIndex);
            storage.save(taskList);
            String s = String.format("Noted. I've removed this task:\n%s\n%s",
                    removedTask.toString(), taskList.numTaskToString());
            return s;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid input. Please provide a valid task index or check that the task exists.");
        }
    }
}
