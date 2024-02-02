package nollid.commands;

import java.util.ArrayList;

import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;

/**
 * MarkCommand class represents a command for marking a task as done.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class MarkCommand extends Command {
    /**
     * ArrayList containing command arguments.
     */
    private final ArrayList<String> argsList;

    /**
     * Constructor for MarkCommand.
     *
     * @param argsList ArrayList containing command arguments.
     */
    public MarkCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    /**
     * Overrides the execute method from the Command class.
     * Executes the command to mark a task as done.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NollidException {
        // This means that the user has not supplied any number with the command
        if (argsList.size() == 1) {
            throw new InvalidArgumentException("Please enter the task you wish to mark as done!\n" + "Usage: mark "
                    + "[task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(argsList.get(1));
                tasks.setDone(taskIndex, true);

                String response = "Good job! I've marked this task as done: \n"
                        + "\t " + tasks.get(taskIndex - 1).toString();

                storage.update(tasks);

                return response;
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Please enter a number for the mark command.\n" + "Usage: mark "
                        + "[task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidArgumentException("Are you sure that's a valid task number? (Tip: use 'list' to "
                        + "check the number of your task!)\n" + "Usage: mark [task number]");
            }
        }
    }
}
