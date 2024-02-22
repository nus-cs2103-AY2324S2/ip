package nollid.commands;

import java.util.ArrayList;

import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;
import nollid.tasks.Task;

/**
 * DeleteCommand class represents a command for deleting a task.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class DeleteCommand extends Command {
    /**
     * Constant string providing usage hint for the DeleteCommand.
     */
    public static final String USAGE_HINT = "Usage: delete task_number";
    /**
     * ArrayList containing command arguments.
     */
    private final ArrayList<String> argsList;

    /**
     * Constructor for DeleteCommand.
     *
     * @param argsList ArrayList containing command arguments.
     */
    public DeleteCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    /**
     * Overrides the execute method from the Command class.
     * Executes the command to delete a task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NollidException {
        int taskIndex = getTaskNumberFromArgument() - 1;
        try {
            Task removedTask = tasks.remove(taskIndex);

            String message = "Alright, the following task has been removed:\n" + "\t" + removedTask + "\n";
            message += tasks.summary();

            storage.update(tasks);

            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Are you sure that's a valid task number? (Tip: use 'list' to "
                    + "check the number of your task!)\n" + USAGE_HINT);
        }
    }

    /**
     * Retrieves the task number from the input arguments for the delete command.
     *
     * @return The task number to be deleted.
     * @throws InvalidArgumentException If the task number is not provided or is not a valid integer.
     */
    private int getTaskNumberFromArgument() throws InvalidArgumentException {
        if (this.argsList.size() == 1) {
            throw new InvalidArgumentException(
                    "Please enter the task you wish to delete.\n" + "Usage: delete [task number]");
        }

        try {
            return Integer.parseInt(this.argsList.get(1));
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(
                    "Please enter a number for the delete command.\n" + "Usage: delete [task number]");
        }
    }
}
