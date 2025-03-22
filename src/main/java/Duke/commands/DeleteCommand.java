package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private String[] words;

    /**
     * Constructs a DeleteCommand object with the specified array of words.
     * @param words The array of words representing the delete command.
     */
    public DeleteCommand(String[] words) {
        super();
        this.words = words;
    }

    /**
     * Checks if a string is numeric.
     *
     * @param s The string to check.
     * @return True if the string is numeric, false otherwise.
     */
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return A message indicating the success of the delete operation.
     * @throws DukeException If an error occurs during the execution of the command.
     */
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean isOneWord = words.length == 1;
        if (isOneWord) {
            throw new EmptyDescriptionException("delete");
        }
        String deleteIdx = words[1].trim();
        int currentIdx = tasks.getNumberOfTasks();
        if (!isNumeric(deleteIdx)) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx = Integer.parseInt(deleteIdx) - 1;
        boolean isInvalidIndex = taskIdx >= currentIdx || taskIdx < 0;
        if (isInvalidIndex) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        currentIdx--;
        Task deletedTask = tasks.delete(taskIdx);
        storage.rewriteFile(tasks.getTasks());
        return ui.deleteMessage(deletedTask, currentIdx);
    }
}
