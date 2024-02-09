package nollid.commands;

import java.util.ArrayList;

import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;
import nollid.tasks.Task;

/**
 * FindCommand class represents a command to search for tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    /**
     * Constant string providing usage hint for the FindCommand.
     */
    public static final String USAGE_HINT = "Usage: find [keyword]";

    /**
     * ArrayList containing command arguments.
     */
    private final ArrayList<String> argsList;

    /**
     * Constructor for FindCommand.
     *
     * @param argsList ArrayList containing command arguments.
     */
    public FindCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    /**
     * Overrides the execute method from the Command class.
     * Executes the command to search for tasks containing a specific keyword.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NollidException {
        checkKeywordProvided();
        checkOnlyOneKeyword();

        String keyword = argsList.get(1);

        TaskList results = new TaskList();

        // If task description contains keyword, add to result list
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                results.add(t);
            }
        }

        return getResultsString(results);
    }

    private void checkKeywordProvided() throws InvalidArgumentException {
        if (argsList.size() == 1) {
            throw new InvalidArgumentException("Please enter a keyword to search for.\n" + FindCommand.USAGE_HINT);
        }
    }

    private void checkOnlyOneKeyword() throws InvalidArgumentException {
        if (argsList.size() > 2) {
            throw new InvalidArgumentException("Please enter only 1 keyword.\n" + FindCommand.USAGE_HINT);
        }
    }

    private String getResultsString(TaskList results) {
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

        return outputMessage.toString();
    }
}
