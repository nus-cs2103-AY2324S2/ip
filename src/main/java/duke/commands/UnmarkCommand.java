package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.common.Utils;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * The UnmarkCommand class represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {

    /**
     * Marks a task as not done yet in a task list.
     * 
     * @param tasks An ArrayList of Task objects, representing a list of tasks.
     * @param input Contains the index of the task being marked.
     */
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (!isValidCommandLength(input)) {
            throw new CommandException(
                    "Please specify which task. (format: unmark <task no.>)");
        }

        if (!Utils.isInteger(input[1])) {
            throw new CommandException("Task number not found! (format: unmark <task no.>)");
        }

        int index = Integer.parseInt(input[1]) - 1;

        // check if index is within bounds
        if (index >= tasks.size() || index < 0) {
            throw new CommandException("Task not found!");
        }
        // check if there's no change in status
        if (!tasks.get(index).getStatus()) {
            throw new CommandException(
                    "The task you're unmarking was not marked to begin with... I'm not changing anything.");
        } else {
            tasks.get(index).setStatus(false);
            super.commandResponse = Ui.printOutput("OK, I've marked this task as not done yet: ",
                    tasks.get(index).toString());
        }
    }

}
