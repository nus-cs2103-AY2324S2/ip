package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import lucky.tasks.Task;
import lucky.ui.Ui;

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

        if (!isValidCommandLength(input)) {
            throw new CommandException(
                    "Please specify the keyword. (Format: find <keyword>)");
        }

        HashSet<Task> matchingTasks = new HashSet<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(input[1].trim())) {
                matchingTasks.add(task);
            }
            if (input[1].charAt(0) == '#' && task.getTags().contains(input[1].trim())) {
                matchingTasks.add(task);
            }
        }

        if (!matchingTasks.isEmpty()) {
            super.commandResponse = Ui.printList(new ArrayList<>(matchingTasks));
        } else {
            super.commandResponse = Ui.printOutput(String.format(
                    "Hmmm... I can't find any task that corresponds to the keyword '%s'",
                    input[1]));
        }
    }
}
