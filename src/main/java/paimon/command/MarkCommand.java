package paimon.command;

import paimon.ChatException;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.util.UiHandler;


/**
 * Represents a command to mark a specific task in the task list as done. The task to be marked
 * is identified by its index provided by the user.
 */
public class MarkCommand extends Command {
    private final String indexString;

    /**
     * Constructs a MarkCommand with the index of the task to be marked as done.
     *
     * @param indexString The string representation of the task's index in the task list.
     */
    public MarkCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Executes the mark command by parsing the index string to an integer, validating it,
     * and then marking the corresponding task as done if it's not already marked.
     * Notifies the user of the action through the UI handler.
     *
     * @param taskList The task list from which a task will be marked as done.
     * @return A String to be displayed.
     * @throws ChatException If the index string cannot be converted to a valid integer,
     *                       or if the index is out of bounds for the task list.
     */
    public String execute(TaskList taskList) throws ChatException {
        try {
            int markIndex = Integer.parseInt(indexString);
            if (markIndex >= 1 && markIndex <= taskList.getSize()) {
                Task markTask = taskList.getTask(markIndex - 1);
                if (taskList.markTask(markIndex - 1, true)) {
                    return UiHandler.getMarkTaskMessage(markTask.getTask(), true);
                } else {
                    throw new ChatException("Traveller, this task is already marked as done!");
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
     * @return false always, as marking a task as done does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }

}
