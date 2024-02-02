package nollid.commands;

import java.util.ArrayList;

import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;

/**
 * DeleteCommand class represents a command for deleting a task.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class DeleteCommand extends Command {
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
        // This means that the user has not supplied any number with the command
        if (this.argsList.size() == 1) {
            throw new InvalidArgumentException("Please enter the task you wish to delete!\n"
                    + "Usage: delete [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(this.argsList.get(1));

                String message = "Alright, the following task has been removed:\n"
                        + "\t" + tasks.remove(taskIndex - 1).toString() + "\n";

                message += tasks.summary();

                storage.update(tasks);

                return message;
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Please enter a number for the delete command.\n"
                        + "Usage: delete [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidArgumentException("Are you sure that's a valid task number? (Tip: use 'list' to "
                        + "check the number of your task!)\n" + "Usage: delete [task number]");
            }
        }
    }
}
