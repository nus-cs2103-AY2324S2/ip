package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.ui.Ui;

/**
 * This class represents the command that finds tasks that matches the keyword input.
 */
public class FindCommand extends Command {
    /**
     * Finds a task that contains a keyword similar to the user's input.
     * 
     * @param tasks An ArrayList of tasks.
     * @param input An array of input containing the keyword.
     */
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {

        if (input.length < 2) {
            throw new CommandException(
                    "Please specify the keyword. (Format: find <keyword>)");
        }

        ArrayList<Task> matchingTask = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(input[1].trim())) {
                matchingTask.add(task);
            }
        }

        if (!matchingTask.isEmpty()) {
            super.commandResponse = Ui.printList(matchingTask);
        } else {
            super.commandResponse = Ui.printOutput(String.format(
                    "Hmmm... I can't find any task that corresponds to the keyword '%s'",
                    input[1].toString()));
        }
    }
}
