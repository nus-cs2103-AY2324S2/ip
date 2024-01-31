package nollid.commands;

import java.util.ArrayList;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;
import nollid.tasks.Task;

/**
 * FindCommand class represents a command to search for tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    public static final String USAGE_HINT = "Usage: find [keyword]";
    private final ArrayList<String> argsList;

    public FindCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        // If no keyword provided, throw error
        if (argsList.size() == 1) {
            throw new InvalidArgumentException("Please enter a keyword to search for.\n"
                    + FindCommand.USAGE_HINT);
        }

        // If more than 1 keyword, throw error
        if (argsList.size() > 2) {
            throw new InvalidArgumentException("Please enter only 1 keyword.\n"
                    + FindCommand.USAGE_HINT);
        }

        String keyword = argsList.get(1);
        // List to store tasks that contain keyword in their description
        TaskList results = new TaskList();

        // Search through each task's description for the keyword
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                results.add(t);
            }
        }

        // List items in the results list
        StringBuilder outputMessage = new StringBuilder("Here are the matching tasks in your list:\n");
        if (results.isEmpty()) {
            outputMessage = new StringBuilder("There are no matching tasks in your list.");
        }

        for (int i = 0; i < results.size(); i++) {
            if (i < results.size() - 1) {
                outputMessage.append(i + 1).append(".").append(results.get(i).toString()).append("\n");
            } else {
                outputMessage.append(i + 1).append(".").append(results.get(i).toString());
            }
        }
        ui.sendMessage(outputMessage.toString());
    }
}
