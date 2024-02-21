package pingmebot.command;

import java.util.ArrayList;

import pingmebot.PingMeException;
import pingmebot.Storage;
import pingmebot.TaskList;
import pingmebot.UI;
import pingmebot.task.Task;



/**
 * Represents a command for the user to find a task from the task list in the application.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a find command with a specified keyword which the user wants to find in his/her list.
     *
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to help user locate the task in his/her list, if it exists.
     * It also responds the corresponding reply to the user via the ui object.
     *
     * @param tasks The task list containing all the tasks which the user has added.
     * @param storage The local storage holding all the information about the entire task list.
     * @param ui The object responsible for displaying corresponding response to the user.
     * @throws PingMeException If an error occurs during the execution process.
     */
    public void execute(TaskList tasks, Storage storage, UI ui) throws PingMeException {
        ArrayList<Task> matchingTasks = tasks.findMatchingTask(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showError("No matching results found!");
        } else {
            ui.listMatchingText(matchingTasks);
        }
    }

    /**
     * Returns a boolean to see if the 2 FindCommand objects are the same by comparing the keyword that it holds.
     *
     * @param obj The other command to be compared to.
     * @return A boolean to see if the 2 FindCommand objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        FindCommand otherCommand = (FindCommand) obj;
        return this.keyword.equals(otherCommand.keyword);
    }
}
