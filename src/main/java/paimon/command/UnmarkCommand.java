package paimon.command;

import paimon.ChatException;
import paimon.util.UiHandler;
import paimon.task.Task;
import paimon.task.TaskList;

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
     * @param ui       The UI handler for interacting with the user and displaying feedback.
     * @throws ChatException If the index string cannot be converted to a valid integer,
     *                       or if the index is out of bounds for the task list.
     */
    public void execute(TaskList taskList, UiHandler ui) throws ChatException {
        try {
            int markIndex = Integer.parseInt(indexString);
            if (markIndex >= 1 && markIndex <= taskList.getSize()) {
                Task markTask = taskList.getTask(markIndex - 1);
                if (taskList.markTask(markIndex - 1, false)) {
                    ui.displayMarkTaskMessage(markTask.getTask(), true);
                } else {
                    System.out.println("Traveller, this task is already unmarked.");
                }
            } else {
                System.out.println("Sorry Traveller, that task does not exist");
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