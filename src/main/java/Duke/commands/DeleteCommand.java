package duke.commands;

import duke.util.TaskList;
import duke.util.UI;
import duke.util.Storage;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskIndexException;

/**
 * Constructs a DeleteCommand with the given array of words.
 *
 * @param words An array of words representing the command and its arguments.
 */
public class DeleteCommand extends Command {
    private String[] words;
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
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    public DeleteCommand(String[] words) {
        super();
        this.words = words;
    }
    /**
     * Executes the delete command.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param s The storage.
     * @return Always returns false.
     * @throws DukeException If an error occurs during execution.
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage s) throws DukeException {
        int currentIdx = tasks.list().size();
        if (words.length == 1 || !isNumeric(words[1])) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx2 = Integer.parseInt(words[1]) - 1;
        if (taskIdx2 >= currentIdx || taskIdx2 < 0) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        currentIdx--;
        ui.displayDelete(tasks.delete(taskIdx2), currentIdx);
        s.rewriteFile(tasks.list());
        return false;
    }
}
