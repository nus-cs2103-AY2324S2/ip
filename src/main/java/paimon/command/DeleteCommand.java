package paimon.command;

import paimon.ChatException;
import paimon.task.TaskList;
import paimon.util.UiHandler;


/**
 * Represents a command to delete a task from the task list. The task to be deleted
 * is identified by an index provided by the user.
 */
public class DeleteCommand extends Command {
    private final String indexString;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param indexString The string representation of the task's index in the task list.
     */
    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Executes the delete command by converting the index string to an integer,
     * validating it, and then deleting the corresponding task from the task list.
     * Notifies the user of the action through the UI handler.
     *
     * @param taskList The task list from which a task will be deleted.
     * @return A String to be displayed.
     * @throws ChatException If the index string cannot be converted to a valid integer,
     *                       or if the index is out of bounds for the task list.
     */
    public String execute(TaskList taskList) throws ChatException {
        try {
            int deleteIndex = Integer.parseInt(indexString);
            if (deleteIndex >= 1 && deleteIndex <= taskList.getSize()) {
                taskList.deleteTask(deleteIndex - 1);
                return UiHandler.getDeleteTaskMessage(taskList.getSize());
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
     * @return false always, as deleting a task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }

}
