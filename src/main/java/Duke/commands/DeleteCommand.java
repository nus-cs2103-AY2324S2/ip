package duke.commands;


import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute Delete Command
 */
public class DeleteCommand extends Command {
    private String[] words;

    /**
     * Constructor for DeleteCommand class
     * @param words words in UserInput to execute the command
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

    @Override
    public String executeForString(TaskList tasks, UI ui, Storage s) throws DukeException {
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
        if (taskIdx >= currentIdx || taskIdx < 0) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        currentIdx--;
        Task deletedTask = tasks.delete(taskIdx);
        s.rewriteFile(tasks.getTasks());
        return ui.deleteMessage(deletedTask, currentIdx);
    }
}
