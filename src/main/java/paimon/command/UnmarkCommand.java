package paimon.command;

import paimon.ChatException;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.util.UiHandler;


/**
 * Represents a command to mark a specific task in the task list as not done. The task to be unmarked
 * is identified by an index provided by the user.
 */
public class UnmarkCommand extends Command {
    private final String indexString;

    /**
     * Constructs an UnmarkCommand with the index of the task to be marked as not done.
     *
     * @param indexString The string representation of the task's index in the task list.
     */
    public UnmarkCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Executes the unmark command by parsing the index string to an integer, validating it,
     * and then marking the corresponding task as not done if it's not already unmarked.
     * Provides feedback to the user through the UI handler.
     *
     * @param taskList The task list from which a task will be marked as not done.
     * @return A String to be displayed.
     * @throws ChatException If the index string cannot be converted to a valid integer,
     *                       or if the index is out of bounds for the task list.
     */
    public String execute(TaskList taskList) throws ChatException {
        try {
            int markIndex = Integer.parseInt(indexString);
            if (markIndex >= 1 && markIndex <= taskList.getSize()) {
                Task markTask = taskList.getTask(markIndex - 1);
                if (taskList.markTask(markIndex - 1, false)) {
                    return UiHandler.getMarkTaskMessage(markTask.getTask(), true);
                } else {
                    throw new ChatException("Traveller, this task is already unmarked.");
                }
            } else {
                throw new ChatException("Sorry Traveller, that task does not exist");
            }
        } catch (NumberFormatException e) {
            throw new ChatException("Sorry Traveller, your input is invalid");
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("Sorry Traveller, your index is out of bounds");
        }
    }

    /**
     * Indicates that executing this command does not signal the application to exit.
     *
     * @return false always, as unmarking a task as not done does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }

}
