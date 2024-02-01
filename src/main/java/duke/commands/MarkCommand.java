package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.common.Utils;
import duke.tasks.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (input.length < 2) {
            throw new CommandException(
                    "Please specify which task. (format: mark/unmark <task no.>)");
        }

        if (!Utils.isInteger(input[1])) {
            throw new CommandException("Task number not found! (format: mark/unmark <task no.>)");
        }

        int index = Integer.parseInt(input[1]) - 1;

        // check if index is within bounds
        if (index >= tasks.size()) {
            throw new CommandException("Task not found!");
        }
        // check if there's no change in status
        if (tasks.get(index).getStatus()) {
            throw new CommandException(
                    "The task was already marked as done. I'm not changing anything.");
        } else {
            tasks.get(index).setStatus(true);
            Ui.printOutput("Nice! I've marked this task as done:", tasks.get(index).toString());
        }
    }

}
