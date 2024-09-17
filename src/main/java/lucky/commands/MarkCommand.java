package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;

import lucky.common.Utils;
import lucky.tasks.Task;
import lucky.ui.Ui;

/**
 * The MarkCommand class is responsible for marking a task as done in a task list.
 */
public class MarkCommand extends Command {
    /**
     * Marks the task as done in a list of tasks.
     *
     * @param tasks An ArrayList of Task objects. This represents the list of tasks that the user
     *              has.
     * @param input An array of strings representing the user's input. This contains the index-1
     *              of the task being marked.
     */

    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (!isValidCommandLength(input)) {
            throw new CommandException(
                    "Please specify which task. (format: mark <task no.>)");
        }

        if (!Utils.isInteger(input[1])) {
            throw new CommandException("Task number not found! (format: mark <task no.>)");
        }

        int index = Integer.parseInt(input[1]) - 1;

        // check if index is within bounds
        if (index >= tasks.size() || index < 0) {
            throw new CommandException("Task not found!");
        }
        // check if there's no change in status
        if (tasks.get(index).getIsMarked()) {
            throw new CommandException(
                    "The task was already marked as done. I'm not changing anything.");
        } else {
            tasks.get(index).setMarked(true);
            super.commandResponse = Ui.printOutput(
                    "Nice! I've marked this task as done:", tasks.get(index).toString());
        }
    }

}
